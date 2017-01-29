$(function() {
    "use strict";

    $('#fullpage').fullpage({
        sectionsColor: [
            '#000',
            '#599E48',
            '#573413',
            '#599E48',
            '#573413',
            '#599E48',
            '#573413',
            '#000'
        ],
        anchors: [
            'start-page',
            'first-page',
            'second-page',
            'third-page',
            'fourth-page',
            'fifth-page',
            'sixth-page',
            'end-page'
        ],
        loopHorizontal: false
    });

    // Video
    var $vid = $('#demo-vid');
    var $arrow = $('.fp-controlArrow');
    $vid.on('playing', function() {
        $arrow.css('display', 'none');
    });
    $vid.on('ended pause', function() {
        $arrow.css('display', 'block');
    });

    // Bottom images && top titles
    var $sections = $('.section');
    $sections.each(function(i, section) {
        var $slides = $(section).find('.slide');
        var width = 100 / $slides.length + '%';
        $slides.each(function(j, slide) {
            $(slide).find('.image-bottom-wrapper').each(function(k, wrapper) {
                $(wrapper).css('width', width);
            });
            $(slide).find('.topTitle').each(function(l, title) {
                $(title).css('width', width);
            });
            $(slide).find('.overview').each(function(m, overview) {
                $(overview).css('width', width);
            });
        });
    });

    // Full width ending
    $('.full-width-text').fitText();

    setOverview();
});

// Overview
function setOverview() {
    $('.overview').each(function(i, overview) {
        var windowHeight = window.innerHeight;
        var height = $(overview).closest('.section').find('h1').first().height();
        $(overview).css('height', windowHeight - height);
    });
}