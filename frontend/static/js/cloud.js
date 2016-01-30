var Cloud = (function() {
	var count = 0;

	var Cloud = {
		'create' : function() {
			var cloud = $('<div class="cloud"></div>').appendTo('#clouds');
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

		'open' : function(cloud, dream) {
			$.get('templates/opened-dream.html').done(function(template) {
				var html = doT.template(template)({
					'dream' : dream,
					'origin' : window.location.origin
				});
				cloud.animate({
					left : ($(window).width()/2-$(cloud).width()/2)+'px',
				}, 40);
				cloud.css({
					'-webkit-animation': 'none',
					'-moz-animation': 'none',
					'-o-animation': 'none',
					'animation' : 'none'
				});
				cloud.addClass('active-cloud');
				cloud.html(html);

				$('#clouds').unbind("click");				
				Cloud.bindFollow();
			});
		},

		'bind' : function() {
			$('#clouds').on('click', '> div', function(e) {
				var cloud = $(this);
				Dream.service.findRandom(function(dream) {
					Cloud.open(cloud, dream);
				});
			    e.stopPropagation();
			});
		},

		'bindFollow' : function() {
			$('.follow').unbind('click').click(function() {
				var button = $(this);
				Dream.service.follow(button.data('id'), function() {
					button.prop('disabled', true);
				});
			});
		}
	};
	return Cloud; 
})();
Cloud.bind();