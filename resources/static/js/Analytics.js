        var ContentList;
        var ContentMovements;
        var OnsiteTempTotalOne = 0;
        var OnsiteTempTotalTwo = 0;
        var OnsiteTempTotalThree = 0;
        var OnlineTempTotalOne = 0;
        var OnlineTempTotalTwo = 0;
        var OnlineTempTotalThree = 0;
        
       function LoadTotals(){  
    	   for (var i = 0; i < ContentList.length; i++) {
    		   switch (ContentList[i].seasonCode) {
    		     case 1:
    			   OnsiteTempTotalOne = OnsiteTempTotalOne + ContentList[i].onsiteSales;
    			   OnlineTempTotalOne = OnlineTempTotalOne + ContentList[i].onlineSales;
    			   break;
    		     case 2:
        		   OnsiteTempTotalTwo = OnsiteTempTotalTwo + ContentList[i].onsiteSales;
        		   OnlineTempTotalTwo = OnlineTempTotalTwo + ContentList[i].onlineSales;
        	       break;
        	     case 3:
        		   OnsiteTempTotalThree = OnsiteTempTotalThree + ContentList[i].onsiteSales;
        		   OnlineTempTotalThree = OnlineTempTotalThree + ContentList[i].onlineSales;
        	       break;
        	       }
    		   }
    	   }

        function LoadGraphics(){
            var ctx = document.getElementById('myChart').getContext('2d');
                    var myChart = new Chart(ctx, {
                        type: 'line',               
                        data: {
                            labels: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio','Julio'
                            ,'Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
                            datasets: [{
                                label: 'Importe de ventas de manera presencial',
                                data: [ContentList[0].onsiteSales, ContentList[1].onsiteSales, ContentList[2].onsiteSales,
                                    ContentList[3].onsiteSales, ContentList[4].onsiteSales, ContentList[5].onsiteSales,
                                    ContentList[6].onsiteSales, ContentList[7].onsiteSales, ContentList[8].onsiteSales,
                                    ContentList[9].onsiteSales, ContentList[10].onsiteSales, ContentList[11].onsiteSales],
                                backgroundColor: [
                                    'rgba(38, 235, 14, 0.7)'
                                ],
                                borderColor: [
                                    'rgba(38, 235, 14, 1)'
                                ],
                                borderWidth: 2
                            }]
                        },
                        options: {
                        	legend: {
                                labels: {
                                    fontColor: 'white'
                                }
                        	},
                        	scales: {
                                yAxes: [{
                                    ticks: {
                                        beginAtZero: true,
                                        fontColor: 'white'
                                    }
                                }],
                                xAxes: [{
                                    ticks: {
                                        fontColor: 'white'
                                    },
                                }]
                            }
                        }
                    });

                    var ctx = document.getElementById('myChart2').getContext('2d');
                    var myChart = new Chart(ctx, {
                    	 type: 'line',
                        data: {
                            labels: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio','Julio'
                            ,'Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
                            datasets: [{
                                label: 'Importe de ventas de manera online',
                                 data: [ContentList[0].onlineSales, ContentList[1].onlineSales, ContentList[2].onlineSales,
                                      ContentList[3].onlineSales, ContentList[4].onlineSales, ContentList[5].onlineSales,
                                      ContentList[6].onlineSales, ContentList[7].onlineSales, ContentList[8].onlineSales,
                                      ContentList[9].onlineSales, ContentList[10].onlineSales, ContentList[11].onlineSales],
                                backgroundColor: [
                                    'rgba(38, 235, 14, 0.7)'
                                ],
                                borderColor: [
                                    'rgba(38, 235, 14, 1)'
                                ],
                                borderWidth: 2
                            }]
                        },
                        options: {
                        	legend: {
                                labels: {
                                    fontColor: 'white'
                                }
                        	},
                            scales: {
                                yAxes: [{
                                    ticks: {
                                        beginAtZero: true,
                                        fontColor: 'white'
                                    }
                                }],
                                xAxes: [{
                                    ticks: {
                                        fontColor: 'white'
                                    },
                                }]
                            }
                        }
                    });

                    var ctx = document.getElementById('myChart3').getContext('2d');
                    if(ContentMovements[0]===0,ContentMovements[1]===0,ContentMovements[2]===0){
                        document.getElementById("myChart3").style.display="none";
                        document.getElementById("activityTitle").style.display="none";
                    }
                    var myChart = new Chart(ctx, {
                        type: 'doughnut',
                        data: {
                            labels: ['Administradores Activos', 'Supervisores Activos', 'Empleados Activos'],
                            datasets: [{
                                data: [ContentMovements[0], ContentMovements[1], ContentMovements[2]],
                                backgroundColor: [
                                    'rgba(237, 249,0, 1)',
                                    'rgba(0, 237, 249, 1)',
                                    'rgba(198, 0, 89, 1)',
                                ],
                                borderColor: [
                                    'rgba(0, 0, 0, 1)',
                                    'rgba(0, 0, 0, 1)',
                                    'rgba(0, 0, 0, 1)',
                                ],
                                borderWidth: 1
                            }]
                        },
                    options: {
                    	legend: {
                            labels: {
                                fontColor: 'white'
                            }
                    	}
                    }
                    });
                    
                    //diego
                    var ctx = document.getElementById('myChart4').getContext('2d');
                    var myChart = new Chart(ctx, {
                        type: 'bar',
                        data: {
                            labels: ['Temp 1', 'Temp 2', 'Temp 3'],
                            datasets: [{
                                label: 'Monto total presencial',
                               data: [OnsiteTempTotalOne, OnsiteTempTotalTwo, OnsiteTempTotalThree ],
                                backgroundColor: [
                                    'rgba(38, 235, 14, 0.7)',
                                    'rgba(38, 235, 14, 0.7)',
                                    'rgba(38, 235, 14, 0.7)',
                                ],
                                borderColor: [
                                    'rgba(38, 235, 14, 1)',
                                    'rgba(38, 235, 14, 1)',
                                    'rgba(38, 235, 14, 1)',
                                ],
                                borderWidth: 2
                            }]
                        },
                        options: {
                        	legend: {
                                labels: {
                                    fontColor: 'white'
                                }
                        	},
                            scales: {
                                yAxes: [{
                                    ticks: {
                                        beginAtZero: true,
                                        fontColor: 'white'
                                    }
                                }],
                                xAxes: [{
                                    ticks: {
                                        fontColor: 'white'
                                    },
                                }]
                            }
                        }
                    });
                    
                    var ctx = document.getElementById('myChart5').getContext('2d');
                    var myChart = new Chart(ctx, {
                        type: 'bar',
                        data: {
                            labels: ['Temp 1', 'Temp 2', 'Temp 3'],
                            datasets: [{
                                label: 'Monto total Online',
                               data: [OnlineTempTotalOne, OnlineTempTotalTwo, OnlineTempTotalThree ],
                                backgroundColor: [
                                    'rgba(38, 235, 14, 0.7)',
                                    'rgba(38, 235, 14, 0.7)',
                                    'rgba(38, 235, 14, 0.7)',
                                ],
                                borderColor: [
                                    'rgba(38, 235, 14, 1)',
                                    'rgba(38, 235, 14, 1)',
                                    'rgba(38, 235, 14, 1)',
                                ],
                                borderWidth: 2
                            }]
                        },
                        options: {
                        	legend: {
                                labels: {
                                    fontColor: 'white'
                                }
                        	},
                            scales: {
                                yAxes: [{
                                    ticks: {
                                        beginAtZero: true,
                                        fontColor: 'white'
                                    }
                                }],
                                xAxes: [{
                                    ticks: {
                                        fontColor: 'white'
                                    },
                                }]
                            }
                        }
                    });
                    
                    var ctx = document.getElementById('myChart6').getContext('2d');
                    var myChart = new Chart(ctx, {
                        type: 'line',
                        data: {
                            labels: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio','Julio'
                            ,'Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
                            datasets: [{
                               label: 'Tarifas promedio',
                               data: [ContentList[0].averageRate, ContentList[1].averageRate, ContentList[2].averageRate,
                                    ContentList[3].averageRate, ContentList[4].averageRate, ContentList[5].averageRate,
                                    ContentList[6].averageRate, ContentList[7].averageRate, ContentList[8].averageRate,
                                    ContentList[9].averageRate, ContentList[10].averageRate, ContentList[11].averageRate],
                                backgroundColor: [
                                    'rgba(38, 235, 14, 0.0)'
                                ],
                                borderColor: [
                                    'rgba(38, 235, 14, 1)'
                                ],
                                borderWidth: 2
                                },{
                            	label: 'Tarifas promedio objetivo',
                                data: [ContentList[0].expectedRate, ContentList[1].expectedRate, ContentList[2].expectedRate,
                                     ContentList[3].expectedRate, ContentList[4].expectedRate, ContentList[5].expectedRate,
                                     ContentList[6].expectedRate, ContentList[7].expectedRate, ContentList[8].expectedRate,
                                     ContentList[9].expectedRate, ContentList[10].expectedRate, ContentList[11].expectedRate],
                                 backgroundColor: [
                                     'rgba(255, 0, 0, 0.0)'
                                 ],
                                 borderColor: [
                                     'rgba(255, 0, 0, 1)'
                                 ],
                                 borderWidth: 2
                                 }]
                    },
                    options: {
                    	legend: {
                            labels: {
                                fontColor: 'white'
                            }
                    	},
                    	scales: {
                    		yAxes: [{
                    			ticks: {
                    				beginAtZero: true,
                    				scalesize:10,
                    				scaleOverride : true,
                    				fontColor: 'white'
                    				}
                                }],
                                xAxes: [{
                                    ticks: {
                                        fontColor: 'white'
                                    },
                                }]
                            }
                        }
                    });
                    
                    var ctx = document.getElementById('myChart7').getContext('2d');
                    var myChart = new Chart(ctx, {
                        type: 'line',
                        data: {
                            labels: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio','Julio'
                            ,'Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
                            datasets: [{
                               label: 'Porcentaje online',
                               data: [ContentList[0].onlinePercentage, ContentList[1].onlinePercentage, ContentList[2].onlinePercentage,
                                    ContentList[3].onlinePercentage, ContentList[4].onlinePercentage, ContentList[5].onlinePercentage,
                                    ContentList[6].onlinePercentage, ContentList[7].onlinePercentage, ContentList[8].onlinePercentage,
                                    ContentList[9].onlinePercentage, ContentList[10].onlinePercentage, ContentList[11].onlinePercentage],
                                backgroundColor: [
                                    'rgba(255, 0, 0, 0)'
                                ],
                                borderColor: [
                                    'rgba(38, 235, 14, 1)'
                                ],
                                borderWidth: 2
                                },{
                            	label: 'Porcentaje online objetivo',
                                data: [ContentList[0].expectedOnlinePercentage, ContentList[1].expectedOnlinePercentage, ContentList[2].expectedOnlinePercentage,
                                     ContentList[3].expectedOnlinePercentage, ContentList[4].expectedOnlinePercentage, ContentList[5].expectedOnlinePercentage,
                                     ContentList[6].expectedOnlinePercentage, ContentList[7].expectedOnlinePercentage, ContentList[8].expectedOnlinePercentage,
                                     ContentList[9].expectedOnlinePercentage, ContentList[10].expectedOnlinePercentage, ContentList[11].expectedOnlinePercentage],
                                 backgroundColor: [
                                     'rgba(255, 0, 0, 0)'
                                 ],
                                 borderColor: [
                                     'rgba(255, 0, 0, 1)'
                                 ],
                                 borderWidth: 2
                                 }]
                    },
                    options: {
                    	legend: {
                            labels: {
                                fontColor: 'white'
                            }
                    	},
                    	scales: {
                    		yAxes: [{
                    			ticks: {
                    				beginAtZero: true,
                    				scalesize:10,
                    				scaleOverride : true,
                    				fontColor: 'white'
                    				}
                                }],
                                xAxes: [{
                                    ticks: {
                                        fontColor: 'white'
                                    },
                                }]
                            }
                        }
                    });
                    
                    var ctx = document.getElementById('myChart8').getContext('2d');
                    var myChart = new Chart(ctx, {
                        type: 'line',
                        data: {
                            labels: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio','Julio'
                            ,'Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
                            datasets: [{
                               label: 'Porcentaje presencial',
                               data: [ContentList[0].onsitePercetnage, ContentList[1].onsitePercetnage, ContentList[2].onsitePercetnage,
                                    ContentList[3].onsitePercetnage, ContentList[4].onsitePercetnage, ContentList[5].onsitePercetnage,
                                    ContentList[6].onsitePercetnage, ContentList[7].onsitePercetnage, ContentList[8].onsitePercetnage,
                                    ContentList[9].onsitePercetnage, ContentList[10].onsitePercetnage, ContentList[11].onsitePercetnage],
                                backgroundColor: [
                                    'rgba(38, 235, 14, 0)'
                                ],
                                borderColor: [
                                    'rgba(38, 235, 14, 1)'
                                ],
                                borderWidth: 2
                                },{
                            	label: 'Porcentaje presencial objetivo',
                                data: [ContentList[0].expectedOnsitePercetnage, ContentList[1].expectedOnsitePercetnage, ContentList[2].expectedOnsitePercetnage,
                                     ContentList[3].expectedOnsitePercetnage, ContentList[4].expectedOnsitePercetnage, ContentList[5].expectedOnsitePercetnage,
                                     ContentList[6].expectedOnsitePercetnage, ContentList[7].expectedOnsitePercetnage, ContentList[8].expectedOnsitePercetnage,
                                     ContentList[9].expectedOnsitePercetnage, ContentList[10].expectedOnsitePercetnage, ContentList[11].expectedOnsitePercetnage],
                                 backgroundColor: [
                                     'rgba(255, 0, 0, 0)'
                                 ],
                                 borderColor: [
                                     'rgba(255, 0, 0, 1)'
                                 ],
                                 borderWidth: 2
                                 }]
                    },
                    options: {
                    	legend: {
                            labels: {
                                fontColor: 'white'
                            }
                    	},
                    	scales: {
                    		yAxes: [{
                    			ticks: {
                    				beginAtZero: true,
                    				scalesize:10,
                    				scaleOverride : true,
                    				fontColor: 'white'
                    				}
                                }],
                                xAxes: [{
                                    ticks: {
                                        fontColor: 'white'
                                    },
                                }]
                            }
                        }
                    });
        }
