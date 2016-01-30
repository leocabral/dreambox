import json
import cgi
import webapp2
import hashlib

from google.appengine.ext import ndb
from google.appengine.ext import db

from lib import ndb_json
from dateutil.parser import parse

class Dreamers(ndb.Model):
	name = ndb.StringProperty(indexed=True)
	last_name = ndb.StringProperty(indexed=True)
	birthday = ndb.DateProperty(indexed=True)
	dreaming_since = ndb.DateTimeProperty(auto_now_add=True)
	nickname = ndb.StringProperty()
	email = ndb.StringProperty(indexed=True)
	password = ndb.StringProperty()
	organization = ndb.StringProperty(indexed=True)

	@classmethod
	def find_all(cls):
		return Dreamers.query()

	@classmethod
	def find(csl, _id):
		return Dreamers.get_by_id(_id)


class DreamersAPI(webapp2.RequestHandler):
	def list(self):
		self.response.headers['Content-Type'] = 'application/json'
		self.response.out.write(ndb_json.dumps(Dreamers.find_all()))

	def post(self):
		birthday = None
		if self.request.get('birthday') != None :
			birthday = parse(self.request.get('birthday'))

		dreamer = Dreamers(
			name = self.request.get('name'), 
			last_name = self.request.get('last_name'), 
			birthday = birthday,
			nickname = self.request.get('nickname'),
			email = self.request.get('email'),
			password = hashlib.sha224(self.request.get('password')).hexdigest(),
			organization = self.request.get('organization'))

		self.response.headers['Content-Type'] = 'application/json'
		self.response.out.write(ndb_json.dumps(dreamer.put().get()))

	def get(self, dreamer_id):
		self.response.headers['Content-Type'] = 'application/json'
		self.response.out.write(ndb_json.dumps(Dreamers.find(int(dreamer_id))))
