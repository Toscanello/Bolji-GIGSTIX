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
                        <a class="nav-link" aria-current="page" href="/#/home-page">Home</a>
                    </li>
                    <div v-if="(korisnik===null)">
                        <li class="nav-item">
                            <a class="nav-link" href="/#/login">Prijavi se</a>
                        </li>
                    </div>
                    <div v-if="(korisnik===null)">
                        <li class="nav-item">
                            <a class="nav-link" href="/#/registruj">Registruj se</a>
                        </li>
                    </div>
                    <div v-if="(korisnik!==null)">
                        <div v-if="(korisnik.uloga==='Kupac')||(korisnik.uloga==='Prodavac')">
                            <li class="nav-item">
                                <a class="nav-link" href="/#/edit">Profil</a>
                            </li>
                        </div>
                    </div>
                    <div v-if="(korisnik!==null)">
                        <div v-if="korisnik.uloga==='Kupac'">
                            <li class="nav-item">
                            <a class="nav-link" href="/#/karte">Pregled karata</a>
                            </li>
                        </div>
                    </div>
                    <div v-if="(korisnik!==null)">
                        <div v-if="(korisnik.uloga==='Prodavac')">
                            <li class="nav-item">
                                <a class="nav-link" href="/#/regManifestacije">Dodaj manifestaciju</a>
                            </li>
                        </div>
                    </div>
                    <div v-if="(korisnik!==null)">
                        <div v-if="(korisnik.uloga==='Administrator')">
                            <li class="nav-item">
                                <a class="nav-link" href="/#/registruj">Registruj prodavca</a>
                            </li>
                        </div>
                    </div>
                    <div v-if="(korisnik!==null)">
                        <div v-if="(korisnik.uloga==='Administrator')">
                            <li class="nav-item">
                                <a class="nav-link" href="/#/pregledKomentara">Pregled komentara</a>
                            </li>
                        </div>
                    </div>
                    <div v-if="(korisnik!==null)">
                        <div v-if="(korisnik.uloga==='Administrator')">
                            <li class="nav-item">
                                <a class="nav-link" href="/#/korisniciPregled">Pregled korisnika</a>
                            </li>
                        </div>
                    </div>
                    <div v-if="(korisnik !==null)">
                        <li class="nav-item">
                            <p id="korisnik">Dobro dosli {{korisnik.ime}}</p>
                        </li>
                    </div>
                    <div v-if="(korisnik !== null)">
                        <li class="nav-item">
                                <a id="logout" class="nav-link" href="/#/logout">Logout</a>
                        </li>
                    </div>
                </ul>
            </div>
        </div>
    </nav>
    `,
    mounted(){
        this.korisnik=JSON.parse(localStorage.getItem('korisnik'))
    }
})