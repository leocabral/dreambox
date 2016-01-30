import json
import random
import webapp2

from google.appengine.ext import ndb
from google.appengine.ext import db
from lib import ndb_json
from models.dreamers import Dreamers

class Dreams(ndb.Model):
	name = ndb.StringProperty()
	name_search = ndb.ComputedProperty(lambda self: self.name.lower())
	description = ndb.StringProperty()
	date = ndb.DateTimeProperty(auto_now_add=True)
	dreamer = ndb.IntegerProperty()
	tags = ndb.JsonProperty()
	followers = ndb.JsonProperty()

	@classmethod
	def find_all(cls):
		return Dreams.query()

	@classmethod
	def find(csl, _id):
		return Dreams.get_by_id(_id)

	@classmethod
	def search_by_name(cls, term):
		return Dreams.query(ndb.AND(Dreams.name_search >= term.lower(), Dreams.name_search <= term.lower() + u'\ufffd'))

	@classmethod
	def of(cls, dreamer_id):
		return Dreams.query(Dreams.dreamer == dreamer_id)

	@classmethod
	def random(cls):
		return random.sample(Dreams.query().fetch(keys_only=True), 1)[0].get()

	@classmethod
	def update(cls, _id, dream_new):
		dream = Dreams.find(_id)
		dream.name = dream_new.name
		dream.description = dream_new.description
		dream.tags = dream_new.tags

		return dream.put().get()

	@classmethod
	def add_follower(cls, _id, helper):
		dream = Dreams.find(_id)

		if not dream.followers:
			dream.followers = []

		dream.followers.append(helper)

		dream.put()


class DreamsAPI(webapp2.RequestHandler):
	def list(self):
		self.response.headers['Content-Type'] = 'application/json'
		self.response.out.write(ndb_json.dumps(Dreams.find_all()))

	def post(self):
		dream = Dreams(
					name = self.request.get('name'),
					description = self.request.get('description'),
					tags = self.request.get('tags'),
					dreamer = int(self.request.get('dreamer')))

		self.response.headers['Content-Type'] = 'application/json'
		self.response.out.write(ndb_json.dumps(dream.put().get()))

	def mantain(self, dream_id):
		if self.request.method == 'GET':
			return DreamsAPI.get(self, dream_id)
		if self.request.method == 'DELETE':
			return DreamsAPI.delete(self, dream_id)
		if self.request.method == 'PUT':
			return DreamsAPI.put(self, dream_id)


	def get(self, dream_id):
		self.response.headers['Content-Type'] = 'application/json'
		self.response.out.write(ndb_json.dumps(Dreams.find(int(dream_id))))

	def search(self, term):
		self.response.headers['Content-Type'] = 'application/json'
		self.response.out.write(ndb_json.dumps(Dreams.search_by_name(term)))

	def list_by_dreamer(self, dreamer_id):
		self.response.headers['Content-Type'] = 'application/json'
		self.response.out.write(ndb_json.dumps(Dreams.of(int(dreamer_id))))

	def random(self):
		self.response.headers['Content-Type'] = 'application/json'
		random_dream = Dreams.random()
		dream = ndb_json.loads(ndb_json.dumps(random_dream))

		dreamer = Dreamers.find(random_dream.dreamer)
		dream['dreamer'] = ndb_json.loads(ndb_json.dumps(dreamer))
		del dream['dreamer']['password']

		self.response.out.write(ndb_json.dumps(dream))

	def put(self, dream_id):
		Dreams.update(int(dream_id), Dreams(name = self.request.get('name'), description = self.request.get('description'), tags = json.loads(self.request.get('tags'))))

	def delete(self, dream_id):
		Dreams.find(int(dream_id)).key.delete()

	def add_follower(self, dream_id):
		Dreams.add_follower(int(dream_id), int(self.request.get('helper')))
