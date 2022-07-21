define([], function() {
    'use strict'

    const V_TICKS_STYLE = {
        fontColor: '#495057',
        fontStyle: 'bold'
    }

    const V_MODE = 'index'
    const V_INTERSECT = true

    return (function() {
        const chartType = 'bar';
        const defaultOptions = {
            maintainAspectRatio: false,
            tooltips: {
                mode: V_MODE,
                intersect: V_INTERSECT
            },
            hover: {
                mode: V_MODE,
                intersect: V_INTERSECT
            },
            legend: {
                display: false
            },
            scales: {
                yAxes: [{
                    gridLines: {
                        display: true,
                        lineWidth: '4px',
                        color: 'rgba(0, 0, 0, .2)',
                        zeroLineColor: 'transparent'
                    },
                    ticks: $.extend({
                        beginAtZero: true,
                    }, V_TICKS_STYLE)
                }],
                xAxes: [{
                    display: true,
                    gridLines: {
                        display: false
                    },
                    ticks: V_TICKS_STYLE
                }]
            }
        };

        return {
            draw: function(element, labels) {
                let existsChart = Chart.getChart($(element));
                if(existsChart) {
                    existsChart.destroy();
                }
                const V_DATASET = [];
                $(arguments).each(function(idx, argument) {
                    if(idx < 2) return;

                    V_DATASET.push({
                        backgroundColor: argument.color,
                        borderColor: argument.color,
                        data: argument.data,
                    })
                }).promise().done(function() {
                    this.chart = new Chart($(element), {
                        type: chartType,
                        options: defaultOptions,
                        data: {
                            labels: labels,
                            datasets: V_DATASET
                        }
                    })
                })
            }
        }
    });
    return BarChart;
});