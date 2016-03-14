<%@page import="ir.ramtung.polling.*"%>
<%@page import="java.util.*"%>

<html>
<head>
<title>New Poll</title>

<script>
var nextId = 0;
function addInput() {
	var newDiv = document.createElement('div');
	newDiv.id = "choiceDiv_" + nextId;
    newDiv.innerHTML = "<input type='text' name='choices[]' size=30/>" +
		"&nbsp;<a href='javascript:void(0)' onclick='removeInput(" + nextId + ");'><small>Remove</small></a>" +
		"&nbsp;<a href='javascript:void(0)' onclick='moveUp(" + nextId + ");'><small>Move Up</small></a>" +
		"&nbsp;<a href='javascript:void(0)' onclick='moveDn(" + nextId + ");'><small>Move Down</small></a>";
    document.getElementById('choicesDiv').appendChild(newDiv);
    nextId++;
}

function moveUp(inputIdx) {
	var choiceDivs = document.getElementById('choicesDiv').getElementsByTagName('div');
	for (i = 1; i < choiceDivs.length; i++)
		if (choiceDivs[i].id == ("choiceDiv_" + inputIdx)) {
			document.getElementById('choicesDiv').insertBefore(choiceDivs[i], choiceDivs[i-1]);
		}
}

function moveDn(inputIdx) {
	var choiceDivs = document.getElementById('choicesDiv').getElementsByTagName('div');
	for (i = 0; i < choiceDivs.length - 1; i++)
		if (choiceDivs[i].id == ("choiceDiv_" + inputIdx)) {
			if (i == choiceDivs.length - 1)
				document.getElementById('choicesDiv').append(choiceDivs[i]);
			else
				document.getElementById('choicesDiv').insertBefore(choiceDivs[i], choiceDivs[i+2]);
			return;
		}
}

function removeInput(inputIdx) {
	var choiceDivs = document.getElementById('choicesDiv').childNodes;
	for (i = 0; i < choiceDivs.length; i++)
		if (choiceDivs[i].id == ("choiceDiv_" + inputIdx))
			document.getElementById('choicesDiv').removeChild(choiceDivs[i]);
}

function Validate()
{
    var frm = document.voteForm;

    var sub = frm.subject.value;
    if (sub == "") {
        alert("Subject is a required field");
        return false;
    }
	
	var choiceInputs = document.getElementById('choicesDiv').getElementsByTagName('input');
	for (i = 0; i < choiceInputs.length; i++)
		if (choiceInputs[i].value == "") {
			alert("Empty choices are not allowed");
			return false;
		}

    return true;
}

function saveClicked()
{
    if (Validate())
		document.voteForm.submit();
}

</script>
</head>
<body onLoad="addInput();">
	<h3>New Poll Information:</h3><p>
	
	<form action="save-poll.jsp" method="POST" name="voteForm">
		Subject:<br>
		<input type="text" name="subject" size=50/><p>
		
		<div id="choicesDiv"> Choices:
		</div>
		<a href="javascript:void(0)" onclick="addInput();"><small>Another Choice</small></a>
		<p>
		<input type="button" value="Save Poll" onclick="saveClicked();"/>
	</form>
</body>
</html>