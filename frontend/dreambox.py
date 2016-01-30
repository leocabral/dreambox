import webapp2
from handlers.handlers import *
from models import *


app = webapp2.WSGIApplication([
    ('/', MainPage),
    webapp2.Route(r'/api/dreams', handler=dreams.DreamsAPI, handler_method='list', methods=['GET']),
    webapp2.Route(r'/api/dreams', handler=dreams.DreamsAPI, handler_method='post', methods=['POST']),
    webapp2.Route(r'/api/dreams/random', handler=dreams.DreamsAPI, handler_method='random', methods=['GET']),
    webapp2.Route(r'/api/dreams/of/<:\d+>', handler=dreams.DreamsAPI, handler_method='list_by_dreamer', methods=['GET']),
    webapp2.Route(r'/api/dreams/<:\d+>', handler=dreams.DreamsAPI, handler_method='mantain', methods=['GET', 'PUT', 'DELETE']),
    webapp2.Route(r'/api/dreams/search/<:.+>', handler=dreams.DreamsAPI, handler_method='search', methods=['GET']),
    webapp2.Route(r'/api/dreamers', handler=dreamers.DreamersAPI, handler_method='list', methods=['GET']),
    webapp2.Route(r'/api/dreamers', handler=dreamers.DreamersAPI, handler_method='post', methods=['POST']),
    webapp2.Route(r'/api/dreamers/<:\d+>', handler=dreamers.DreamersAPI, handler_method='get', methods=['GET'])
], debug=True)
