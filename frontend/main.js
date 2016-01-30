var clouds = 0;
var createCloud = function () {
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
	
	clouds++;
}
var buildCreator = function (interval) {
	var creator = setInterval(function() {
		createCloud();
		if (cloud >= 40) {
			clearInterval(creator);
		}
	}, interval);
	return creator;
}
buildCreator(600);
buildCreator(800);
buildCreator(1000);

$('#clouds > div').unbind('click').click(function() {
	
})