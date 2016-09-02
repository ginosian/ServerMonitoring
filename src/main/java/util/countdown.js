/**
 * Created by Martha on 9/2/2016.
 */
function setCountdown () {

    $('#countdown').countdown({until: +59, format: 'yowdHMS',
        layout: '{sn} {sl}', onExpiry: myFunction});
    $('#countdown').removeClass ('is-countdown');
    $('#countdownSpan').show ();
}