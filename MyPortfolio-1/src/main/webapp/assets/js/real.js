$.datepicker.setDefaults({
    dateFormat: 'yy-mm-dd',
    prevText: '이전 달',
    nextText: '다음 달',
    monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    dayNames: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
    showMonthAfterYear: true,
    yearSuffix: '년'
});

$(function(){
    $("#date").datepicker();
    $("#date").change(function(){
        console.log($(this).val());
    })
        // var realtrafficvolumeChart = new Chart($("#Real_Traffic_Chart"), {
        //         type:"line",
        //         options:{
        //             responsive:false,
        //         },
        //         data:{
        //             label:null,
        //             datasets:[{
        //                 label:"실시간 교통량",
        //                 data:null,
        //                 backgroundColor:[
        //                     'rgba(30, 30, 255, 0.2)'
        //                 ]
        //             }],
        //         }
        //     })
        
            $.ajax({
                type:"get",
                url:"",
                success:function(r) {
                console.log(r);
                
                let url = "http://localhost:8760/api/realTrafficInfo?"+date;
                if(time != undefined && time != null && time != '') {
                    url += "&time="+time;
                }
                else if(amount != undefined && amount != null && amount != '') {
                    url += "&amount="+amount;
                }
            
                    $.ajax({
                        type:"get",
                        url:url,
                        success:function(r){
                        console.log(r);

                            if(r.realtrafficlist != null) {
                                let realtrafficlistLabel = new Array();
                                let realtrafficlistData1 = new Array();
                                let realtrafficlistData2 = new Array();
                                for(let i=0; i<r.realtrafficlist.length; i++) {
                                    realtrafficlistLabel.push(r.realtrafficlist[i].conzoneName);
                                    realtrafficlistData1.push(r.realtrafficlist[i].traffic_volume);
                                    realtrafficlistData2.push(r.realtrafficlist[i].start_hour);
                                }
                                realtrafficChart.data.datasets = new Array();
                                realtrafficChart.data.labels = realtrafficlistLabel;
                                realtrafficChart.data.datasets.push({
                                    label:'실시간 교통량', data:realtrafficlistData1,
                                    backgroundColor:['rgba(30, 30, 255, 0.7']
                                });
                                realtrafficChart.data.datasets.push({
                                    label:'최종 시간', data:realtrafficlistData2,
                                    backgroundColor:['rgba(255, 30, 30, 0.7']
                                });
                                realtrafficChart.update();
                            }

                        //     let Real_Traffic_Chart = new Chart($("#Real_Traffic_Chart"), {
                        //         type:"bar",
                        //         options:{
                        //             responsive:false,
                        //         },
                        //         data:{
                        //             labels:"구간별 실시간 교통상황",
                        //                 datasets:[{
                        //                     label:"구간1",
                        //                     data:50,
                        //                     backgroundColor:['rgba(30,255,30,0.7)']
                        //                 },
                        //                 {
                        //                     labels:"구간2",
                        //                     data:70,
                        //                     backgroundColor:['rgba(30,300,30,0.7)']
                        //                 },
                        //                 {
                        //                     labels:"구간3",
                        //                     data:65,
                        //                     backgroundColor:['rgba(50,80,30,0.3)']
                        //                 }
                        //             ]
                        //         }
                        //     }
                        // )
                            
                    }
                
                })
            }
             
        })
    })    