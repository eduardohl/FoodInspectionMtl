<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<head>
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<script	src="//maps.googleapis.com/maps/api/js?key=AIzaSyBPMAfbp0i0v8f92z9yBj-hrjMdV32a-Zg&sensor=false"></script>
	<script src="resources/script/jquery-2.0.3.min.js"></script>
	<script src="resources/script/jquery-ui-1.10.4.custom.min.js"></script>
	<script src="resources/script/fim.js"></script>
	<link href="resources/style/fim.css" rel="stylesheet" type="text/css" />
	<link href="resources/style/jquery-ui-1.10.4.custom.min.css" rel="stylesheet" type="text/css" />
</head>

<body>

	<div id="page_header" class="page-block">
		<h1>Food Inspection Montréal</h1>
		<span>Mapping restaurant inspection offenses</span>
	</div>

	<div id="filter" class="page-block">
		<form id="filter-form">
			<ul>
				<li><label for="startDate">Start date:</label> <input
					id="startDate" name="startDate" class="date-picker" type="date">
				</li>
				<li><label for="endDate">End date:</label> <input
					id="endDate" name="endDate" class="date-picker" type="date">
				</li>
				<li>
					<label for="category">Category:</label>
					<select id="category" name="category">
						<c:forEach var="category" items="${categories}">
							<option value="${category}">${category}</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<button type="button" id="update-map-button">Update Map</button>
				</li>
			</ul>
		</form>
	</div>

	<div id="content" class="page-block">
		<div id="map-canvas" ></div>
	</div>

	<div id="footer" class="page-block">
		<ul>
			<li><a href="#" onclick="return false;" id="what-is-this">What is this</a></li>
			<li><a href="http://www2.publicationsduquebec.gouv.qc.ca/dynamicSearch/telecharge.php?type=2&file=/P_29/P29.html">Loi sur les produits alimentaires</a></li>
			<li><a href="http://www.linkedin.com/in/eduardolomonaco/en">Author</a></li>
			<li><a href="https://github.com/eduardohl/FoodInspectionMtl">Get the code</a></li>
		</ul>
	</div>
	
	<div id="overlay">
		<p>TODO</p><a href="#" id="overlay-close">X</a>
	</div>
</body>
</html>