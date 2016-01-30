var Dream = (function () {

	$('#salvar-sonho').unbind('click').click(function() {
		Dream.service.save({
	       name: $('#titulo-sonho').val(),
	       description: $('#descricao-sonho').val(),
	       dreamer: Dreamer.id()
	    });
	});

	$('#novo-sonho').unbind('click').click(function(e) {
		var newDream = Cloud.create();
		var content = {};
		content.avatar = Dreamer.avatar();
		content.title = '<input type="text" placeholder="Titulo do sonho"/></div>';
		content.description = '<textarea class="textarea-dream"></textarea>';
		content.buttons = '<button type="button" class="confirmation-button">Criar</button>';

		Cloud.openContent(newDream, content);

	    e.stopPropagation();
	});

	$('#new-dream-cloud').unbind('click').click(function(e) {
	    e.stopPropagation();
	});

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