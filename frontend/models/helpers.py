import json
import cgi
import webapp2
import hashlib

from google.appengine.ext import ndb
from google.appengine.ext import db

from lib import ndb_json
from dateutil.parser import parse

class Helpers(ndb.Model):
	name = ndb.StringProperty(indexed=True)
	last_name = ndb.StringProperty(indexed=True)
	helper_since = ndb.DateTimeProperty(auto_now_add=True)
	email = ndb.StringProperty(indexed=True)
	password = ndb.StringProperty()

	@classmethod
	def find_all(cls):
		return Helpers.query()

	@classmethod
	def find(csl, _id):
		return Helpers.get_by_id(_id)

	@classmethod
	def update(cls, _id, helper_new):
		helper = Helpers.find(_id)
		helper.name = helper_new.name
		helper.last_name = helper_new.last_name

		return helper.put().get()


class HelpersAPI(webapp2.RequestHandler):
	def list(self):
		self.response.headers['Content-Type'] = 'application/json'
		self.response.out.write(ndb_json.dumps(Helpers.find_all()))

	def post(self):
		birthday = None
		if self.request.get('birthday') != None :
			birthday = parse(self.request.get('birthday'))

		helper = Helpers(
			name = self.request.get('name'), 
			last_name = self.request.get('last_name'), 
			email = self.request.get('email'),
			password = hashlib.sha224(self.request.get('password')).hexdigest())

		self.response.headers['Content-Type'] = 'application/json'
		self.response.out.write(ndb_json.dumps(helper.put().get()))

	def mantain(self, helper_id):
		if self.request.method == 'GET':
			return HelpersAPI.get(self, helper_id)
		if self.request.method == 'DELETE':
			return HelpersAPI.delete(self, helper_id)
		if self.request.method == 'PUT':
			return HelpersAPI.put(self, helper_id)

	def get(self, helper_id):
		self.response.headers['Content-Type'] = 'application/json'
		self.response.out.write(ndb_json.dumps(Helpers.find(int(helper_id))))

	def put(self, helper_id):
		birthday = None
		if self.request.get('birthday') != None :
			birthday = parse(self.request.get('birthday'))
		Helpers.update(int(helper_id), Dreams(
			name = self.request.get('name'), 
			last_name = self.request.get('last_name')))

	def delete(self, helper_id):
		Helpers.find(int(helper_id)).key.delete()

