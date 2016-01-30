import json
import cgi
import webapp2
import hashlib
import logging

from google.appengine.ext import ndb
from google.appengine.ext import db

from lib import ndb_json
from dateutil.parser import parse

class Administrators(ndb.Model):
	email = ndb.StringProperty(indexed=True)
	password = ndb.StringProperty()
	since = ndb.DateTimeProperty(auto_now_add=True)

	@classmethod
	def find_all(cls):
		return Administrators.query()

	@classmethod
	def find(cls, _id):
		return Administrators.get_by_id(_id)

	@classmethod
	def find_by_email_and_password(cls, _email, _password):
		query_result = Administrators.query(ndb.AND(Administrators.email == _email, Administrators.password == _password)).fetch(1)
		if len(query_result) > 0:
			return query_result[0]
		else:
			return None

	@classmethod
	def empty(cls):
		return len(Administrators.query().fetch(1)) == 0

class AdministratorsAPI(webapp2.RequestHandler):

	def authenticate(self):
		logging.info('Trying to authenticate')
		email = self.request.get('email')
		password = self.request.get('password')
		password_hashed = hashlib.sha224(password).hexdigest()
		admin = Administrators.find_by_email_and_password(email, password_hashed)
		if admin is None:
			logging.info('Admin not found: email: ' + email + ' password: ' + password)

		if admin is None and email == 'admin' and password == 'dream-box-admin' and Administrators.empty():
			logging.info('Creating admin user')
			admin = Administrators(
					email = 'admin',
					password = password_hashed).put().get()

		self.response.headers['Content-Type'] = 'application/json'
		# TODO: nao deve retornar a senha
		self.response.out.write(ndb_json.dumps(admin))

