$(document).ready(function() {
  $("#meal_select").change(function() {
    $("#datepicker").val("");
    $('.meal_time').removeClass('stop');
    $('.meal_time').removeClass('full');
    $('.meal_time').removeClass('notEnough');
    $('.meal_time').removeClass('reserved');
    $('.meal_time').removeClass('meal-time-style');
    $(".remainSeatNumber").text("");
    $('.meal_time').find('.remainSeat').removeClass('hidden-div');
    $('.meal_time').find('.remainSeat').removeClass('hasFull');
    $('.meal_time').find('.remainSeat').removeClass('hasReserved');
    $('.meal_time').find('.remainSeat').removeClass('not-business');
  });
});
