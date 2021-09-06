$(function(){

    // getRainfallInfo("2020-01-01");
    var rainfallChart = new Chart($("#rainfall_Chart"), {
        type:"line",
        options:{
            responsive:false,
        },
        data:{
            labels:null,
            datasets:null
            }
    })

    var windChart = new Chart($("#wind_Chart"), {
        type:"line",
        options:{
            responsive:false,
        },
        data:{
            labels:null,
            datasets:null
            }
    })


    var temperatureChart = new Chart($("#temperature_Chart"), {
        type:"line",
        options:{
            responsive:false,
        },
        data:{
            labels:null,
            datasets:null
            }
    })

        $("#temperature_select").change(function(){
            let rainfall = $(this).find("option:selected").val();
            getTemperatureChart(rainfall);
        });
        
        //getTemperatureChart("강우량별");
        //getAccidentCnt("사고건수")
    // function getTemperatureChart(tm_month){ 

    //     let url = "http://localhost:8760/api/accident/temperature/rainfall/chart?tm_month=10&rainfall=0.65",
    //         if(tm_month != undefined && tm_month != null && tm_month != '') {
    //             url += "&tm_month="+tm
    //         }
    //         else if(rainfull != undefined && rainfull != null && rainfull != '') {
    //             url += "&rainfull="+rainfull
    //         }
    //     let url2 = "http://localhost:8760/api/accidentCnt/chart?region_acc_cnt=74.0961&region_month=1";
    //         if(region_month != undefined && region_month != null && region_month != '') {
    //             url += "&region_month="+region_month
    //         }
            $.ajax({
                type:"get",
                url:"/api/theothers/rainfall?tm_month=3&rainfall=0.65&region_acc_cnt=65.5658&region_month=3",
                success:function(r) {
                    console.log(r);

                    let timeLabel = new Array();
                    let rainfallData = new Array();
                    let accidentCntData = new Array();

                    if(r.rainfallList != null) {
                       for(let i=0; i<r.rainfallList.length; i++) {
                        timeLabel.push(r.rainfallList[i].tm_month);                                
                        rainfallData.push(r.rainfallList[i].rainfall);
                        accidentCntData.push(r.accidentCntList[i].region_acc_cnt);
                        }
                        rainfallChart.data.datasets = new Array();
                        rainfallChart.data.labels = timeLabel,
                        rainfallChart.data.datasets.push({
                            label:'강수량', data:rainfallData,                                
                            backgroundColor:['rgba(30,30,255,0.7)'],
                            })
                        rainfallChart.data.datasets.push({
                            label:'교통사고 건수', data:accidentCntData,
                            backgroundColor:['rgba(255,30,255,0.7)'],
                            });

                            rainfallChart.update();   
                           }
                        }
                    })
            $.ajax({
                type:"get",
                url:"/api/theothers/wind?tm_month=4&wind=3&region_month=4&region_acc_cnt=70.9912",
                success:function(r) {
                    console.log(r);

                    let timeLabel = new Array();
                    let windData = new Array();
                    let accidentCntData = new Array();

                    if(r.windList != null) {
                       for(let i=0; i<r.windList.length; i++) {
                            timeLabel.push(r.windList[i].tm_month);
                            windData.push(r.windList[i].wind);
                            accidentCntData.push(r.accidentCntList[i].region_acc_cnt);
                        }
                        windChart.data.datasets = new Array();
                        windChart.data.labels = timeLabel,
                        windChart.data.datasets.push({
                            label:'바람(풍속)', data:windData,
                            backgroundColor:['rgba(30,30,255,0.7)'],
                        });
                        windChart.data.datasets.push({
                            label:'교통사고 건수', data:accidentCntData,
                            backgroundColor:['rgba(255,30,255,0.7)'],
                        });
                        windChart.update();     
                    }
                }
            })
            $.ajax({
                type:"get",
                url:"/api/theothers/temp?temp=7.735483871&tm_month=3&region_month=3&region_acc_cnt=65.5658",
                success:function(r) {
                    console.log(r);

                    let timeLabel = new Array();
                    let temperatureData = new Array();
                    let accidentCntData = new Array();

                    if(r.temperatureList != null) {
                       for(let i=0; i<r.temperatureList.length; i++) {
                            timeLabel.push(r.temperatureList[i].tm_month);
                            temperatureData.push(r.temperatureList[i].temp);
                            accidentCntData.push(r.accidentCntList[i].region_acc_cnt);
                        }
                        temperatureChart.data.datasets = new Array();
                        temperatureChart.data.labels = timeLabel,
                        temperatureChart.data.datasets.push({
                            label:'기온별', data:temperatureData,
                            backgroundColor:['rgba(30,30,255,0.7)'],
                        });
                        temperatureChart.data.datasets.push({
                            label:'교통사고 건수', data:accidentCntData,
                            backgroundColor:['rgba(255,30,255,0.7)'],
                        });
                        temperatureChart.update();     
                    }
                }
            })
})