var add_modal = document.getElementById("add_modal"); 
var add_modal_close = document.getElementById("close_add");

var add_modal_cancel = document.getElementById("cancel_button");

var modalAdd = document.getElementById("add");

add_modal.addEventListener("click", open_modal);
add_modal_close.addEventListener("click", close_modal);
add_modal_cancel.addEventListener("click", close_modal);

function open_modal() {
  modalAdd.style.display = "block";
  console.log("hello add");
}

function close_modal() {
  modalAdd.style.display = "none";
}

var edit_modal = document.getElementById("editbtn");
var modalEdit = document.getElementById("edit");
var edit_modal_cancel = document.getElementById("cancel_edit_button");

edit_modal.addEventListener("click", edit_modaal);
var edit_modal_close = document.getElementById("close_edit");
edit_modal_close.addEventListener("click", close_edit_modal);
edit_modal_cancel.addEventListener("click", close_edit_modal);

function edit_modaal() {
  modalEdit.style.display = "block";
  console.log("hello edit");
}
function close_edit_modal() {
  modalEdit.style.display = "none";
}

var delete_modal = document.getElementById("delete_btn");
var modalDelete = document.getElementById("delete");
var delete_modal_close = document.getElementById("close_delete");

delete_modal.addEventListener("click", delete_modaal);
delete_modal_close.addEventListener("click", close_del);

function delete_modaal() {
  modalDelete.style.display = "block";
  console.log("delete");
}
function close_del() {
  modalDelete.style.display = "none";
}


const myTable = document.getElementById("table");
const header = ["box","Customer Name","Customer #","Invoice #","Invoice Amount","Due Date","predicted payment date","Notes"];
const content = ["FIELD1","customer_name","cust_number","invoice_id","total_open_amount","due_in_date","predicted_date","notes"];
(
    () => {
       fetch(`http://localhost:8080/H2HBABBA2586/fetchData.do`)
       .then(response => {
           response.json()
           .then(response => {
               console.log(response);
               if(response.length > 0){
                    let table = document.createElement("table");
                    let row = document.createElement("tr");


                    header.map((data)=> {
                        let head = document.createElement("th");
                        if(data == "box"){
                            let input = document.createElement("input");
                            input.type = "checkbox";
                            input.id = "selectAll";
                            head.appendChild(input);
                        }else{
                            let text = document.createTextNode(data);
                            head.appendChild(text);
                        } 
                        row.appendChild(head);
                        // row.className = "";
                    })
                    table.appendChild(row);
                    response.map((data)=> {
                        let row = document.createElement("tr");
                        

                        content.map((d) => {
                            let cell = document.createElement("td");
                            if(d == "FIELD1") {
                                let input = document.createElement("input");
                                input.type = "checkbox";
                                input.id = data[d];
                                cell.appendChild(input);
                            }else if(d == "due_in_date"){
                                let date = new Date(data["due_in_date"]);
                                let curr_date = new Date();
                                var month = ["January", "February", "March", "April", "May", "June",
                                "July", "August", "September", "October", "November", "December"][date.getMonth()]; 
                                let text = document.createTextNode(date.getDate() + "-" + month + '-' + date.getFullYear());
                                cell.appendChild(text);
                                if(date< curr_date){
                                    cell.style.color = "#FF5B5B";
                                }
                            }else if(d == "predicted_date"){
                                let text = "";  
                                let date = new Date(data["predicted_date"]);
                                // console.log(date)
                                if(date == "Invalid Date"){
                                    text = document.createTextNode("--");
                                }else{
                                    var month = ["January", "February", "March", "April", "May", "June",
                                    "July", "August", "September", "October", "November", "December"][date.getMonth()]; 
                                    text = document.createTextNode(date.getDate() + "-" + month + '-' + date.getFullYear());
                                }
                                cell.appendChild(text);
                            }else if(d == "total_open_amount" ) {
                                let suffixes = ["", "k", "m", "b","t"];
                                let value = parseInt(parseFloat(data[d]))
                                var suffixNum = Math.floor((""+value).length/3);
                                var shortValue = parseFloat((suffixNum != 0 ? (value / Math.pow(1000,suffixNum)) : value).toPrecision(2));
                                if (shortValue % 1 != 0) {
                                    shortValue = shortValue.toFixed(1);
                                }
                                let text = document.createTextNode(shortValue+suffixes[suffixNum]);
                                cell.appendChild(text);

                            }
                            else{
                                let text = document.createTextNode(data[d]);
                                cell.appendChild(text);
                            }
                            row.appendChild(cell);
                            row.className = "data";
                        })
                        table.appendChild(row);
                    })
                    
                    myTable.appendChild(table);
               }
           })
       })
    }
)()

// function saveAddForm (value) {
// value.preventDefault();
// const addElements=document.getElementById("mainformAdd").elements;
// const addobj={};
// for( var i=0 ; i< addElements;i++){
//     var item = addElements.isDefaultNamespace(i);
//     addobj[item.name]=item.value;
// }
// console.log("submit",addobj,addElements.length);
// }



 