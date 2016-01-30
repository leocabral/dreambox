import webapp2
from handlers.handlers import *
from models import *


app = webapp2.WSGIApplication([
    ('/', MainPage),
    webapp2.Route(r'/api/dreams', handler=dreams.DreamsAPI, handler_method='list'),
    webapp2.Route(r'/api/dreams/<:\d+>', handler=dreams.DreamsAPI, handler_method='get'),
    webapp2.Route(r'/api/dreamers', handler=dreamers.DreamersAPI, handler_method='list'),
    webapp2.Route(r'/api/dreamers/<:\d+>', handler=dreamers.DreamersAPI, handler_method='get')
], debug=True)
