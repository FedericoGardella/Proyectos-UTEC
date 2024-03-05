//const popoverTriggerList = document.querySelectorAll('[data-bs-toggle="popover"]')
//const popoverList = [...popoverTriggerList].map(popoverTriggerEl => new bootstrap.Popover(popoverTriggerEl))

const list = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'))
const popoverList = list.map((el) => {
  let opts = {
    animation: false,
  }
  if (el.hasAttribute('data-bs-content-id')) {
    opts.content = document.getElementById(el.getAttribute('data-bs-content-id')).innerHTML;
    opts.html = true;
  }
  new bootstrap.Popover(el, opts);
})