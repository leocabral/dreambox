import json
import cgi

from google.appengine.ext import ndb
from google.appengine.ext import db

from lib import ndb_json
import webapp2

class Dreamers(ndb.Model):
    name = ndb.StringProperty(indexed=True)
    lastname = ndb.StringProperty(indexed=True)
    birthday = ndb.DateProperty(indexed=True)
    dreamingSince = ndb.DateTimeProperty(auto_now_add=True)

    @classmethod
    def find_all(cls):
        return Dreamers.query()


class DreamersAPI(webapp2.RequestHandler):
    def get(self):
        self.response.out.write(ndb_json.dumps(Dreamers.find_all()))

    def post(self):
        dreamer = Dreamers(name = self.request.get('name'), lastname = self.request.get('lastname'), birthday = self.request.get('birthday'))

        self.response.out.write(ndb_json.dumps(dreamer.put().get()))

