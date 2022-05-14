$(document).ready(function()
{

	$("#alertSuccess").hide();
	$("#alertError").hide();
});


$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------

	
	 $("#alertSuccess").text("");
 	 $("#alertSuccess").hide();
 	 $("#alertError").text("");
 	 $("#alertError").hide();
 	 
 	 
   	// Form validation-------------------
  	
	var status = validateItemForm();
	if (status != true)
	{
		 $("#alertError").text(status);
 		 $("#alertError").show();
 		 
         return;
    }
 
	// If valid------------------------
	
	
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";

	 $.ajax(
 	 {
 		url : "UserAPI",
 		type : type,
 		data : $("#formUser").serialize(),
 		dataType : "text",
	    complete : function(response, status)
        {
   
      			onItemSaveComplete(response.responseText, status);
	    }
	    
     });
     
});
function onItemSaveComplete(response, status)
{
	if (status == "success")
	{
		 var resultSet = JSON.parse(response);
		 
	 	 if (resultSet.status.trim() == "success")
		 {
 				$("#alertSuccess").text("Successfully saved.");
		    	$("#alertSuccess").show();
 				$("#divItemsGrid").html(resultSet.data);
 			
 	 	  } else if (resultSet.status.trim() == "error")
 	 	  {
 	 
 				$("#alertError").text(resultSet.data);
 				$("#alertError").show();
		  }
		  
    } else if (status == "error")
    {
 			$("#alertError").text("Error while saving.");
 			$("#alertError").show();
 			
 	} else
 	{
 			$("#alertError").text("Unknown error while saving..");
 			$("#alertError").show();
    } 

 	$("#hidItemIDSave").val("");
	 $("#formUser")[0].reset();
}
$(document).on("click", ".btnUpdate", function(event)
{
		var id = event.target.id;
		$("#hidItemIDSave").val(id.substring(0, id.length-1));
 		$("#Name").val($(this).closest("tr").find('td:eq(0)').text());
 		$("#Address").val($(this).closest("tr").find('td:eq(1)').text());
 		$("#TelephoneNo").val($(this).closest("tr").find('td:eq(2)').text());
 		$("#PremisesId").val($(this).closest("tr").find('td:eq(3)').text());
});
$(document).on("click", ".btnRemove", function(event)
{
	 $.ajax(
 	{
 		url : "UserAPI",
 		type : "DELETE",
	    data : "UserId=" + $(this).data("UserId"),
 		dataType : "text",
 		complete : function(response, status)
		{
			 onItemDeleteComplete(response.responseText, status);
 		}
	 });
});



function onItemDeleteComplete(response, status)
{
	if (status == "success")
    {
 			var resultSet = JSON.parse(response);
 			
		   if (resultSet.status.trim() == "success")
 		   {
 		   
 				$("#alertSuccess").text("Successfully deleted.");
 				$("#alertSuccess").show();
 				
			    $("#divUserGrid").html(resultSet.data);
			    
			    setTimeout( (function(){alert(43)}, 1000));
 			} else if (resultSet.status.trim() == "error")
 			{
				 $("#alertError").text(resultSet.data);
 				 $("#alertError").show();
		    }
 	} else if (status == "error")
    {
		 $("#alertError").text("Error while deleting.");
 		 $("#alertError").show();
    } else
    {
 		$("#alertError").text("Unknown error while deleting..");
 		$("#alertError").show();
 	}
}
function validateItemForm()
{
	// CODE
	if ($("#Name").val().trim() == "")
 	{
		 return "Insert Item Code.";
    }
    
    
	// NAME
	if ($("#Address").val().trim() == "")
    {
		 return "Insert Item Name.";
 	} 
 	

	// PRICE-------------------------------
	if ($("#TelephoneNo").val().trim() == "")
    {
 		return "Insert Item Price.";
 	}
 	
 	
	
	// DESCRIPTION------------------------
	if ($("#PremisesId").val().trim() == "")
   {
		 return "Insert Item Description.";
   }
   return true;
}
