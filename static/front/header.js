Vue.component("header-comp",{
    data: function(){
        return{
            korisnik:null
        }
    },

    template:`
    <nav class="navbar navbar-expand navbar-dark bg-dark" aria-label="Navbar">
        <div class="container-fluid">
            <a class="navbar-brand" href="http://localhost:9090/#/">Bolji-GIGSTIX</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample02" aria-controls="navbarsExample02" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarsExample02">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item active">
                        <a class="nav-link" aria-current="page" href="/home-page">Home</a>
                    </li>
                    <div v-if="korisnik===''">
                        <li class="nav-item">
                            <a class="nav-link" href="/#/login">Prijavi se</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/#/registruj">Registruj se</a>
                        </li>
                    </div>
                    <div v-if="(korisnik.role==='kupac')||(korisnik.role==='prodavac')">
                        <li class="nav-item">
                            <a class="nav-link" href="/#/edit">Profil</a>
                        </li>
                    </div>
                </ul>
                <form>
                    <input class="form-control" type="text" placeholder="Search" aria-label="Search">
                </form>
            </div>
        </div>
    </nav>
    `
})