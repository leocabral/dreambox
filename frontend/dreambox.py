import webapp2
from handlers.handlers import *

from models import *

app = webapp2.WSGIApplication([
	webapp2.Route(r'/', handler=MainPage, handler_method='home'),
	webapp2.Route(r'/admin', handler=MainPage, handler_method='admin'),
	webapp2.Route(r'/<:.+\.html>', handler=MainPage, handler_method='get'),
	webapp2.Route(r'/api/dreams', handler=dreams.DreamsAPI, handler_method='list', methods=['GET']),
	webapp2.Route(r'/api/dreams', handler=dreams.DreamsAPI, handler_method='post', methods=['POST']),
	webapp2.Route(r'/api/dreams/random', handler=dreams.DreamsAPI, handler_method='random', methods=['GET']),
	webapp2.Route(r'/api/dreams/of/<:\d+>', handler=dreams.DreamsAPI, handler_method='list_by_dreamer', methods=['GET']),
	webapp2.Route(r'/api/dreams/<:\d+>', handler=dreams.DreamsAPI, handler_method='mantain', methods=['GET', 'PUT', 'DELETE']),
	webapp2.Route(r'/api/dreams/search/<:.+>', handler=dreams.DreamsAPI, handler_method='search', methods=['GET']),
	webapp2.Route(r'/api/dreams/<:\d+>/add_follower', handler=dreams.DreamsAPI, handler_method='add_follower', methods=['PUT']),

	webapp2.Route(r'/api/dreamers', handler=dreamers.DreamersAPI, handler_method='list', methods=['GET']),
	webapp2.Route(r'/api/dreamers', handler=dreamers.DreamersAPI, handler_method='post', methods=['POST']),
	webapp2.Route(r'/api/dreamers/<:\d+>', handler=dreamers.DreamersAPI, handler_method='mantain', methods=['GET', 'PUT', 'DELETE']),

	webapp2.Route(r'/api/helpers', handler=helpers.HelpersAPI, handler_method='list', methods=['GET']),
	webapp2.Route(r'/api/helpers', handler=helpers.HelpersAPI, handler_method='post', methods=['POST']),
	webapp2.Route(r'/api/helpers/authenticate', handler=helpers.HelpersAPI, handler_method='authenticate', methods=['POST']),
	webapp2.Route(r'/api/helpers/<:\d+>', handler=helpers.HelpersAPI, handler_method='mantain', methods=['GET', 'PUT', 'DELETE']),
	
	webapp2.Route(r'/api/admin/authenticate', handler=administrators.AdministratorsAPI, handler_method='authenticate', methods=['POST'])
], debug=True)
