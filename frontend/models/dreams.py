import json
import cgi

from google.appengine.ext import ndb
from google.appengine.ext import db


import webapp2

class Dreams(ndb.Model):
    name = ndb.StringProperty(indexed=True)
    description = ndb.StringProperty()
    date = ndb.DateTimeProperty(auto_now_add=True)

    @classmethod
    def find_all(cls):
        return Dreams.query()


class DreamsAPI(webapp2.RequestHandler):
    def get(self):
	dreams = [dict(dream.to_dict(), **dict(id=dream.key.id())) for dream in Dreams.query()]
        self.response.out.write(dreams)

    def post(self):
        dream = Dreams(name = self.request.get('name'), description = self.request.get('description'))

        self.response.out.write(dream.put().get().to_dict())

