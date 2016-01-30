var clouds = 0;
var cloudCreator = setInterval(function() {
	var cloud = $('<div class=" cloud"></div>').appendTo('#clouds');
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
		'moz-animation' : 'moveclouds ' + moveSpeed + 's linear infinite',
		'-o-animation' : 'moveclouds ' + moveSpeed + 's linear infinite'
	});
	
	i++;
	clouds++;
	if (clouds == 10)
		clearInterval(cloudCreator);
}, 1000);


$('#clouds > div').unbind('click').click(function() {
	
})