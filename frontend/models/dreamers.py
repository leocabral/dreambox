import json
import cgi
import webapp2

from google.appengine.ext import ndb
from google.appengine.ext import db

from lib import ndb_json
from dateutil.parser import parse

class Dreamers(ndb.Model):
    name = ndb.StringProperty(indexed=True)
    last_name = ndb.StringProperty(indexed=True)
    birthday = ndb.DateProperty(indexed=True)
    dreaming_since = ndb.DateTimeProperty(auto_now_add=True)

    @classmethod
    def find_all(cls):
        return Dreamers.query()

    @classmethod
    def find(csl, _id):
        return Dreamers.get_by_id(_id)


class DreamersAPI(webapp2.RequestHandler):
    def list(self):
        self.response.out.write(ndb_json.dumps(Dreamers.find_all()))

    def post(self):
        birthday = None
        if self.request.get('birthday') != None :
            birthday = parse(self.request.get('birthday'))

        dreamer = Dreamers(name = self.request.get('name'), last_name = self.request.get('last_name'), birthday = birthday)

        self.response.out.write(ndb_json.dumps(dreamer.put().get()))

    def get(self, dreamer_id):
        self.response.out.write(ndb_json.dumps(Dreamers.find(int(dreamer_id))))
