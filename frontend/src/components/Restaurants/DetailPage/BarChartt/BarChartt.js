import React from 'react'
import "./BarChartt.css"

import {
    Tooltip,
    BarChart,
    XAxis,
    YAxis,
    Legend,
    CartesianGrid,
    Bar,
  } from "recharts";


async function BarChartFunc({restaurant}, stat) {
    const apiKey = 'pri_43a008a020534fd499b364a1b7bacbfb' //pri_169964eea1944217af47f2e03c1dd5ca	
    let restaurantName = restaurant.name

    if (stat){var restaurantAdress = restaurant.street + ' ' + restaurant.city + ' ' + restaurant.country}
    if (!stat){
        //Fake data
        if (restaurantName === "McDonald's"){restaurantAdress = 'Świętokrzyska 35 00-049 Warszawa Poland'}
        if (restaurantName === "Pizza Hut"){restaurantAdress = 'Puławska 14 02-920 Warszawa Poland'}
        if (restaurantName === "Dunkin' Donuts"){restaurantAdress = 'Paryska 37 03-945 Warszawa Poland'}
        if (restaurantName === "KFC"){restaurantAdress = 'Piękna 28/34 00-547 Warszawa Poland'}
        if (restaurantName === "Starbucks"){restaurantAdress = 'Widok 26 00-023 Warszawa Poland'}
        if (restaurantName === "Costa Coffee"){restaurantAdress = 'Aleja Armii Ludowej 14 00-001 Warszawa Poland'}
 
    }

    let params = new URLSearchParams({ 
        'api_key_private': apiKey,
        'venue_name': restaurantName,
        'venue_address':restaurantAdress
        });
        
    const response = await fetch(`https://besttime.app/api/v1/forecasts?${params}`, 
    {
        method: 'POST'
    })

    return response.json();
}

const BarChartt = ({restaurant}) => {
    
    const [isLoading, setLoading] = React.useState(true);
    const [frequency, setFrequency] = React.useState();

    if (isLoading) {
        BarChartFunc({restaurant}, true)
        .then(res=>{

            if (res['status'] === 'error')
            {
                BarChartFunc({restaurant}, false).then(resF=>
                {
                    try {
                        let data = []
                        var date = new Date();

                        for (var i=0; i < 24; i++) {
                                data.push({hour: i.toString(), attendance: resF['analysis'][date.getDay()]['day_raw'][i]})
                            } 
                        setFrequency(data);
                        setLoading(false);
                        } catch (err)
                            {}
                })
            }

            try {
                let data = []
                var date = new Date();

                for (var i=0; i < 24; i++) {
                        data.push({hour: i.toString(), attendance: res['analysis'][date.getDay()]['day_raw'][i]})
                    } 
                setFrequency(data);
                setLoading(false);
                } catch (err)
                    {}
        });
        return(
            <div>
                <h2 class="plot__title">
                    <p class="plot__text">Loading..</p>
                </h2>
            </div>
        )
    }

    return (
        <div class="plot">
            <h2 class="plot__title">
                <p class="plot__text">Expected number of people</p>
            </h2> 
            <div class="plot__main">
                <BarChart
                width={1000}
                height={500}
                data={frequency}
                margin={{
                    top: 60,
                    right: 50,
                    left: 50,
                    bottom: -30,
                }}
                barSize={20}
                >
                <XAxis
                    dataKey="hour"
                    scale="point"
                    padding={{ left: 10, right: 10 }}
                />
                <YAxis />
                <Tooltip />
                <Legend />
                <CartesianGrid strokeDasharray="3 3" />
                <Bar dataKey="attendance" fill="#8884d8" background={{ fill: "#eee" }} />
                </BarChart>
            </div>
        </div>
    
    );
}

export default BarChartt;
