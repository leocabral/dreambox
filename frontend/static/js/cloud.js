var Cloud = (function() {
	var cloudContentTemplate;
	$.get('templates/cloud-content.html').done(function(template) {
		cloudContentTemplate = template;
	});

	var count = 0;

	var randomize = function(cloud) {
		var top = Math.floor((Math.random() * 8)) * 5;
		var moveSpeed = Math.floor((Math.random() * 3) + 6) * 3;
		var scale = Math.floor((Math.random() * 3) + 6) / 10;
		cloud.css({
			'top' : top + '%',
			'-webkit-transform' : 'scale(' + scale + ')',
			'-moz-transform' : 'scale(' + scale + ')',
			'transform' : 'scale(' + scale + ')',
			'opacity' : scale,
			'-webkit-animation' : 'moveclouds ' + moveSpeed + 's linear infinite',
			'-moz-animation' : 'moveclouds ' + moveSpeed + 's linear infinite',
			'-o-animation' : 'moveclouds ' + moveSpeed + 's linear infinite'
		});
	}

	var bind = function() {
		$('#clouds').on('click', '> div', function(e) {
			var cloud = $(this);
			Dream.service.findRandom(function(dream) {
				Cloud.openDream(cloud, dream);
			});
		    e.stopPropagation();
		});
	};

	$(document).on('click',function(e) {
		if($(e.target).parents('.active-cloud').length <= 0) {
			var activeCloud = $('.active-cloud');
			activeCloud.removeClass('active-cloud').html('');
			randomize(activeCloud);
		    bind();
		}
	});

	bind();

	var Cloud = {
		'create' : function() {
			var cloud = $('<div class="cloud"></div>').appendTo('#clouds');
			randomize(cloud);
			count++;
			return cloud;
		},

		'buildCreator' : function(interval) {
			var creator = setInterval(function() {
				Cloud.create();
				if (count >= 50) {
					clearInterval(creator);
				}
			}, interval);
			return creator;
		},
		
		'openContent' : function(cloud, content) {
			var html = doT.template(Cloud.cloudContentTemplate())({ 'content' : content });

			cloud.animate({
				left : Cloud.findCenterPxLeft(cloud),
			}, 40);

			Cloud.removeAnimation(cloud);
			cloud.addClass('active-cloud');
			cloud.html(html);

			$('#clouds').unbind("click");
		},

		'openDream' : function(cloud, dream) {
			$.get('templates/dream-buttons.html').done(function(buttonsTemplate) {
				var buttonsHtml = doT.template(buttonsTemplate)({
					'dreamId' : dream.id,
					'origin' : window.location.origin
				});
				Cloud.openContent(cloud, {
					'avatar' : dream.avatar,
					'title' : dream.name,
					'description' : dream.description,
					'buttons' : buttonsHtml
				});
				$('.follow').unbind('click').click(function() {
					var button = $(this);
					Dream.service.follow(button.data('id'), function() {
						button.prop('disabled', true);
					});
				});
			});
		},
		
		'removeAnimation' : function(cloud) {
			cloud.css({
				'-webkit-animation': 'none',
				'-moz-animation': 'none',
				'-o-animation': 'none',
				'animation' : 'none'
			});
		},

		'findCenterPxLeft' : function(cloud) {
			return ($(window).width()/2-$(cloud).width()/2)+'px';
		},

		'cloudContentTemplate' : function() {
			return cloudContentTemplate;
		}
	};
	return Cloud; 
})();