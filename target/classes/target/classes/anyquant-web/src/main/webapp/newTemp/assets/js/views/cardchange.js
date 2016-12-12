/**
 * Created by peiyulin on 16/5/21.
 */
$(function() {

    var $el = $( '#baraja-el' ),
        baraja = $el.baraja();

    // navigation
    $( '#nav-prev' ).on( 'click', function( event ) {

        baraja.previous();

    } );

    $( '#nav-next' ).on( 'click', function( event ) {

        baraja.next();

    } );

});

$(function() {

    var $el = $( '#baraja-el2' ),
        baraja = $el.baraja();

    // navigation
    $( '#nav-prev' ).on( 'click', function( event ) {

        baraja.previous();

    } );

    $( '#nav-next' ).on( 'click', function( event ) {

        baraja.next();

    } );

});