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

$(function() {
        var realtrafficvolumeChart = new Chart($("#Real_Traffic_Chart"), {
                type:"line",
                options:{
                    responsive:false,
                },
                data:{
                    label:null,
                    datasets:[{
                        label:"실시간 교통량",
                        data:null,
                        backgroundColor:[
                            'rgba(30, 30, 255, 0.2)'
                        ]
                    }],
                }
            })
                
            $("#date").datepicker();
            $("#date").datepicker("setDate", new Date());
            //$("#date").datepicker("option", "maxDate", new Date());

            $("#date").change(function(){
                //console.log($(this).val());
                let region = $("#highway_name_select").find("option:selected").val();
                let date = $("#date").val();
                
            })

            $("#highway_name_select").change(function(){
                let region = $("#highway_name_select").find("option:selected").val();
                let date = $("#date").val();
            
            });

            $.ajax({
            success:function(r){
                console.log(r);

                    let name = new Array();
                    let trafficVolume = new Array();

                    for(let i=0; i<r.data.lenght; i++) {
                        let zonename = r.data[i].conzoneName;
                        let traffic = r.data[i].traffic_volume;
                            name.push(zonename);
                            trafficVolume.push(traffic);
                    }        

                    let Real_Traffic_Chart = new Chart($("#Real_Traffic_Chart"), {
                        type:"bar",
                        options:{
                            responsive:false,
                        },
                        data:{
                            labels:"구간별 실시간 교통상황",
                                datasets:[{
                                    label:"구간1",
                                    data:50,
                                    backgroundColor:['rgba(30,255,30,0.7)']
                                },
                                {
                                    labels:"구간2",
                                    data:70,
                                    backgroundColor:['rgba(30,300,30,0.7)']
                                },
                                {
                                    labels:"구간3",
                                    data:65,
                                    backgroundColor:['rgba(50,80,30,0.3)']
                                }
                            ]
                        }
                    }
                )
                          
            }
        })
    }
) 