import webapp2
from handlers.handlers import *
from models import *


app = webapp2.WSGIApplication([
    ('/', MainPage),
    ('/api/dreams', dreams.DreamsAPI),
    ('/api/dreamers', dreamers.DreamersAPI) 
], debug=True)
