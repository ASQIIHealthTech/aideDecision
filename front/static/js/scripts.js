function getSelected(element) {
  var selected = [...element.options]
    .filter((option) => option.selected)
    .map((option) => option.value);

  return selected;
}

function disbaleEnableInput(selectElement, inputElement, value) {
  if (selectElement.value == value) {
    inputElement.disabled = true;
  } else {
    inputElement.disabled = false;
  }
}

function date(date_cure) {
  date = new Date(date_cure);
  document.getElementById(
    "first-date"
  ).innerHTML = `Cure I : ${date.toDateString()}`;
}

function dategenerator(date_cure, intercure) {
  date = new Date(date_cure);
  document.getElementById(
    "first-date"
  ).innerHTML = `Cure I : ${date.toDateString()}`;
  new_cure = new Date();
  new_cure.setDate(date.getDate() + intercure);
  document.getElementById(
    "second-date"
  ).innerHTML = `Cure II : ${new_cure.toDateString()}`;
}

function date_cure(date, jourProd) {
  console.log("hello");
  date_de_traitement = new Date(date);
  output_date = new Date();
  output_date.setDate(date_de_traitement.getDate() + jourProd - 1);
  document.getElementById("date-apres-prod").innerHTML =
    output_date.toDateString();
}

function createSelect(options, id, value) {
  var select = document.createElement("select");
  select.id = id;
  for (var i = 0; i < Object.keys(options).length; i++) {
    var option = document.createElement("option");
    option.value = value + i;
    option.text = options[option.value];
    select.appendChild(option);
  }

  return select;
}

function createProtocoleSelect(prtocoleArray) {
  var select = document.createElement("select")
  select.id = "protocole-select"
  for (var i = 0; i < prtocoleArray.length; i++) {
    var option = document.createElement("option")
    option.value = "protocole"+ i
    option.text = prtocoleArray[i]
    select.appendChild(option)
  }
  

  return select;

} 

function createErrProtocole(handleldEntity) {
  var label = document.createElement("span");
  label.style.cssText = "front-weight:bold;color:red";
  label.innerHTML = "Coudn't generate " + handleldEntity;

  return label;
}

export {
  getSelected,
  disbaleEnableInput,
  createSelect,
  createProtocoleSelect,
  createErrProtocole
};
