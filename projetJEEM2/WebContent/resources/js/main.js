$(function() {
  // Setup drop down menu
  $('.dropdown-toggle').dropdown();
 
  // Fix input element click problem
  $('.dropdown-menu').click(function(e) {
    e.stopPropagation();
  });

});



function openLoginForm(){
	console.log("open");
	$("#loginDropdown").addClass("open");
}
