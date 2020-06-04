//ajax
 $.ajax({
     url: "https://restcountries.eu/rest/v2/name/uae?fullText=true", //API URL
          success: function(data){
           console.log(data); //Displaying the data to console
           //appending the data to desire class in span tag
      $(".city")[0].append(data[0].name + " ");
      $(".area")[0].append(data[0].area + " ");
      $(".capital")[0].append(data[0].capital + " ");
      $(".population")[0].append(data[0].population + " ");
      $(".nativename")[0].append(data[0].nativeName + " ");
      $(".region")[0].append(data[0].region + " "); 
       $(".callingCodes")[0].append(data[0].callingCodes[0] + " ");
      $(".languages")[0].append(data[0].languages[0].name + " ");
      $(".demonym")[0].append(data[0].demonym + " "); 
       document.querySelector("#flag-container img").src = data[0].flag;
       document.querySelector("#flag-container img").alt = `Flag of ${data[0].name}`;  
    }});
  