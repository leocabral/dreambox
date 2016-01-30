import webapp2
from handlers.handlers import *
from models import *


app = webapp2.WSGIApplication([
    ('/', MainPage),
    ('/api/dreams', dreams.DreamsAPI),
    ('/api/dreams/(\d+)', dreams.DreamAPI),
    ('/api/dreamers', dreamers.DreamersAPI),
    ('/api/dreamers/(\d+)', dreamers.DreamerAPI)
], debug=True)
