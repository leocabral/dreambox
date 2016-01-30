from google.appengine.ext import ndb

class Dream(ndb.Model):
	"""Sub model for representing an Dream."""
	name = ndb.StringProperty(indexed=True)
	description = ndb.StringProperty(indexed=False)
