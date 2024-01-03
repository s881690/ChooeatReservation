(() => {

    const footer = document.createElement("footer");
    footer.classList.add("navbar-fixed-bottom");
	footer.innerHTML = `
        <footer class="footer" id="footer">
            <div class="container-fluid">
                <div class="nav">
                    <div class="col-12 col-lg-2">
                        <figure class="text-center m-4">
                            <p>C H O O E A T !</p>
                        </figure>
                    </div>
                    <div class="col-12 col-lg-4">
                        <figure class="text-center m-4">
                            <p>104 台北市中山區南京東路三段219號5樓</p>
                        </figure>
                    </div>
                    <div class="col-12 col-lg-3">
                        <figure class="text-center m-4">
                            <p>TEL: 02-2712-0589</p>
                        </figure>
                    </div>
                    <div class="col-12 col-lg-3">
                        <figure class="text-center m-4">
                            <p>E-MAIL: CHOOEAT@gmail.com</p>
                        </figure>
                    </div>
                </div>
            </div>
        </footer>
    `;


    const body = document.querySelector('body');
    body.insertAdjacentElement('beforeend', footer);
})();
