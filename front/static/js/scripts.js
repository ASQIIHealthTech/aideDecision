function getSelected(element) {
  var selected = [... element.options]
                  .filter(option => option.selected)
                  .map(option => option.value);
  
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
  document.getElementById("first-date").innerHTML = `Cure I : ${date.toDateString()}`;
}

function dategenerator(date_cure, intercure) {
  date = new Date(date_cure);
  document.getElementById("first-date").innerHTML = `Cure I : ${date.toDateString()}`;
  new_cure = new Date();
  new_cure.setDate(date.getDate() + intercure);
  document.getElementById("second-date").innerHTML = `Cure II : ${new_cure.toDateString()}`;
}

function date_cure(date, jourProd) {
  console.log("hello");
  date_de_traitement = new Date(date);
  output_date = new Date();
  output_date.setDate(date_de_traitement.getDate() + jourProd - 1);
  document.getElementById("date-apres-prod").innerHTML = output_date.toDateString();
}

function createPecSelect(options) {

  var select = document.createElement("select");
  select.id = "pec-select"
  for (var i=0; i <Object.keys(options).length; i++) {
    var option = document.createElement("option")
    option.value = "pec"+i
    option.text = options[option.value]
    select.appendChild(option)
  }

  return select
}

function createProtocoleSelect(options) {

  for(var i=0; i<options.length; i++) {
    var span = document.createElement("span");
    span.innerHTML = options[i]
    var breakLine = document.createElement("br")
    document.getElementById("protocole").appendChild(span)
    document.getElementById("protocole").appendChild(breakLine)
  }
}

export { getSelected, disbaleEnableInput, createPecSelect, createProtocoleSelect }