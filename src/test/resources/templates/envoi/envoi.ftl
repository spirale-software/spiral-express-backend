<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <style>
        .column-1 {
            grid-column: 1 / 2;
            grid-row: 1;
        }

        .column-2 {
            grid-column: 1 / 2;
            grid-row: 1;
        }

        .row {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            grid-gap: 10px;
            grid-auto-rows: minmax(100px, auto);
        }

        .flex-container {
            display: flex;
            flex-wrap: nowrap;
        }

        .flex-container > div {
            width: 100px;
            margin-right: 10px;
        }
    </style>
</head>
<body>
<div style="border: solid 1px; padding: 21px;">

    <span style="display: block; font-weight: bold; font-size: 25px;">Universal transit</span>
    <div style="margin-bottom: 21px">
        <span>Tel: (0032) 555 55 55</span>|
        <span>Email: contact@universal-transit.com</span>|
        <span>Site web: www.universal-transit.com</span>
    </div>

    <div class="row" style="margin-bottom: 21px">
        <div class="column-1">
                <span style="display: block" >Nom: ${expediteurNom}</span>
                <span style="display: block">Prénom: ${expediteurPrenom}</span>
                <span>Adresse: </span>
        </div>

        <div class="column-2">
                <span style="display: block">Nom: </span>
                <span style="display: block">Prénom: </span>
                <span>Adresse: </span>
        </div>
    </div>

    <div>
        <fieldset>
            <legend>Informations sur le coli</legend>
            <div style="margin-bottom: 15px">
                <span style="display: block">Date de prise en charge</span>
                <span> 15/07/2020</span>
            </div>


            <div class="flex-container" style="margin-bottom: 15px">
                <div>
                    <span style="display: block">Nbre d'unité</span>
                    <span>1</span><br>
                </div>
                <div>
                    <span style="display: block">Poids</span>
                    <span>5 kg</span><br>
                </div>
                <div>
                    <span style="display: block">Volume</span>
                    <span>0.90 m³</span><br>
                </div>
            </div>
            <div>
                <span style="display: block">Description du coli</span>
                <span>Blablabla blablabla blablabla</span>
            </div>

        </fieldset>
    </div>

    <div style="margin-top: 50px">
        <span style="display: block">56677889990006655544</span>
        <div style="width: 300px; height: 100px; border: solid 1px;">
            <span style="display: block">|||||||||||||||||||||||</span>
        </div>
    </div>

    <div style="margin-top: 21px">
        <span>Suivez votre envoi sur: www.spiral-express.com</span>
    </div>

</div>
</body>
</html>
