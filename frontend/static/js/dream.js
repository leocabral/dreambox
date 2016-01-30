var Dream = (function () {
	var Dream = {
		
		'service' : {
			'findRandom' : function(callback) {
				$.getJSON('/api/dreams/random').done(callback);
			},

			'find' : function(id, callback) {
				$.getJSON('/api/dreams/' + id).done(callback);
			},

			'follow' : function(id, callback) {
				$.ajax({
					type: 'PUT',
					url: 'api/dreams/' + id + '/add_follower?helper=' + Helper.id()
				}).done(callback);
			},

			'save' : function(dream, callback) {
			    $.post('/api/dreams',dream).done(callback);
			}
		}
	};

	return Dream;
})();