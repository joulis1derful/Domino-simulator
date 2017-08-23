document.getElementById('inputform').addEventListener('submit', validate);

function validate(e) {
    let val = document.getElementById('variant').value;
    if(val === ''){
        console.log(val);
        alert('Please fill the form');
        e.preventDefault();
    }

}