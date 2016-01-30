import json
import cgi

from google.appengine.ext import ndb
from google.appengine.ext import db

from lib import ndb_json
import webapp2

class Dreams(ndb.Model):
    name = ndb.StringProperty(indexed=True)
    description = ndb.StringProperty()
    date = ndb.DateTimeProperty(auto_now_add=True)
    dreamer = ndb.IntegerProperty()

    @classmethod
    def find_all(cls):
        return Dreams.query()

    @classmethod
    def find(csl, _id):
        return Dreams.get_by_id(_id)

    @classmethod
    def search_by_name(cls, term):
        return Dreams.query(ndb.AND(Dreams.name >= term, Dreams.name <= term + u'\ufffd'))


class DreamsAPI(webapp2.RequestHandler):
    def list(self):
        self.response.out.write(ndb_json.dumps(Dreams.find_all()))

    def post(self):
        dream = Dreams(name = self.request.get('name'), description = self.request.get('description'))

        self.response.out.write(ndb_json.dumps(dream.put().get()))

    def get(self, dream_id):
        self.response.out.write(ndb_json.dumps(Dreams.find(int(dream_id))))

    def search(self, term):
        self.response.out.write(ndb_json.dumps(Dreams.search_by_name(term)))
