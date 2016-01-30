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

		anyCloud : function() {
			var clouds = $('#clouds .cloud');
			return $(clouds[Math.trunc(clouds.length * Math.random())]);
		},

		cleanActive : function() {
			var active = $('#clouds .cloud.active-cloud');
			if(active.length) {
				var moveSpeed = Math.floor((Math.random() * 3) + 6) * 3;
				active.removeClass('active-cloud').html('').css({
					'-webkit-animation' : 'moveclouds ' + moveSpeed + 's linear infinite',
					'-moz-animation' : 'moveclouds ' + moveSpeed + 's linear infinite',
					'-o-animation' : 'moveclouds ' + moveSpeed + 's linear infinite',
					'animation' : 'moveclouds ' + moveSpeed + 's linear infinite'
				});
			    Cloud.bind();
			}
		},

		createSigninPopup : function() {
			this.cleanActive();
			var cloud = this.anyCloud();
			$.get('templates/signin.html').done(function(template) {
				var html = doT.template(template)({
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
				var appElm = angular.element(document.querySelector('[ng-app]'));
				var ctrl = $(html).appendTo(cloud);
								
		        appElm.injector().invoke(function($compile, $rootScope) {
		            $compile(ctrl)($rootScope);
		        });

				$('#clouds').unbind("click");				
				Cloud.bindFollow();
			});
		},

		createSignupPopup : function() {
			this.cleanActive();
			var cloud = this.anyCloud();
			$.get('templates/signup.html').done(function(template) {
				var html = doT.template(template)({
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

				var appElm = angular.element(document.querySelector('[ng-app]'));
				var ctrl = $(html).appendTo(cloud);
								
		        appElm.injector().invoke(function($compile, $rootScope) {
		            $compile(ctrl)($rootScope);
		        });

				$('#clouds').unbind("click");				
				Cloud.bindFollow();
			});
		},

		'bind' : function() {
			$('#clouds').on('click', '> div', function(e) {
				var cloud = $(this);
				$.getJSON('/api/dreams/random').success(function(dream) {
					Cloud.open(cloud, dream);
				});
			    e.stopPropagation();
			});
		},

		'bindFollow' : function() {
			$('.follow').unbind('click').click(function() {
				var button = $(this);
				var id = button.data('id');
				$.ajax({
					type: 'PUT',
					url: 'api/dreams/' + id + '/add_follower?helper=' + Helper.id()
				}).done(function() {
				    button.prop('disabled', true);
		        });
			});
		}
	};
	return Cloud; 
})();
Cloud.bind();