$(document).ready(
		function() {
			//window.history.pushState("","", "myOriginalUrlWithNoParams");
			var counter = 2;
			$("#searchSubButton").prop('disabled', true);
			$("#addButton").click(
					function() {
					//	if (counter > 10) {
					//		alert("Only 10 textboxes allow");
					//		return false;
					//	}

						var newTextBoxDiv = $(document.createElement('div'))
								.attr("id", 'TextBoxDiv' + counter);
						newTextBoxDiv.after().html(
								'<label>Search Filter #' + counter + ' : </label>'
										+ '<select name="item_unit[]" id="filterdropdown' + counter+'"><option value="">Select</option><option value="ID">ID</option><option value="Name">Name</option><option value="Experience">Experience</option><option value="Education">Education</option><option value="Skills">Skills</option><option value="Interests">Interests</option><option value="Location">Location</option><option value="Address">Address</option><option value="Resume Summary">Resume Summary</option></select> <input type="text" name="textbox'
										+ counter + '" id="textbox' + counter
										+ '" value="" >');
						newTextBoxDiv.appendTo("#TextBoxesGroup");
						counter++;
					});

			$("#removeButton").click(function() {
				if (counter == 1) {
					alert("No more textbox to remove");
					return false;
				}
				counter--;
				$("#TextBoxDiv" + counter).remove();
			});

			//var q = "PATH:"/app:company_home/st:sites/cm:pebblsoft/cm:documentLibrary/cm:ProfileR//*" AND TYPE:"pf:profile"  AND @pf\:profileName:"Noushad"";
			$("#getButtonValue").click(
					function() {
						var msg = '';
						var queryLabel = '';
						for (i = 1; i < counter; i++) {
							msg += "\n Search Filter #" + i + " : "
									+ $('#filterdropdown'+i).val()+ $('#textbox' + i).val();
							alert("Search Filter Applied!");
							queryLabel += $('#filterdropdown'+i).val()+" : "+ $('#textbox' + i).val() + " | ";
							if($("#filterdropdown" + i).val() == "ID"){ $("#id").val($("#textbox" + i).val());}
							if($("#filterdropdown" + i).val() == "Name"){ $('#name').val($("#textbox" + i).val());}
							if($("#filterdropdown" + i).val() == "Experience"){ $("#experience").val($("#textbox" + i).val());}
							if($("#filterdropdown" + i).val() == "Education"){ $('#education').val($("#textbox" + i).val());}
							if($("#filterdropdown" + i).val() == "Skills"){ $("#skills").val($("#textbox" + i).val());}
							if($("#filterdropdown" + i).val() == "Interests"){ $('#interests').val($("#textbox" + i).val());}
							if($("#filterdropdown" + i).val() == "Location"){ $("#location").val($("#textbox" + i).val());}
							if($("#filterdropdown" + i).val() == "Address"){ $('#address').val($("#textbox" + i).val());}
						}
					//	alert(msg);
						$("#finalQuery").text(queryLabel);
						$("#searchSubButton").prop('disabled', false);
					});
			
			
			//Form submit
			$('#advancedSearchAjxForm').submit(function(){
				
			//	alert("Search Submit");
				// show that something is loading
				$('#srchResponseDiv').html("<b>Loading response...</b>");
				
				// Call ajax for pass data to other place
				$.ajax({
				type: 'POST',
				url: "advancedSearch.action",
				data: $(this).serialize() // getting filed value in serialize form
				})
				.done(function(data){ // if getting done then call.

				//	alert("Recieved the data");
				// show the response
				$('#srchResponseDiv').html(data);

				})
				.fail(function() { // if fail then getting message

				// just in case posting your form failed
				alert( "Posting failed." );

				});

				// to prevent refreshing the whole page page
		//		return false;
				
				
				
			});
			
		});