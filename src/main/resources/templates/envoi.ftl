<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        .titre-section {
            font-weight: bold;
            font-size: 18px;
            padding-bottom: 5px
        }

        table.c {
            table-layout: auto;
            width: 100%;
        }
    </style>
</head>
<body>
<div style="border: solid 1px; padding: 21px;">

    <span style="display: block; font-weight: bold; font-size: 25px;">Universal transit</span>
    <div style="margin-bottom: 21px">
        <span>(+32) 555 55 55</span>
        <span style="padding-left: 3px; padding-right: 3px">|</span>
        <span>contact@universal-transit.com</span>
        <span style="padding-left: 3px; padding-right: 3px">|</span>
        <span>www.universal-transit.com</span>
    </div>

    <#--    <div style="margin-bottom: 21px">-->
    <#--        <div style="margin-bottom: 21px">-->
    <#--            <span class="titre-section">Expéditeur</span>-->
    <#--            <span style="display: block"><strong>Nom:</strong> ${expediteurNom}</span>-->
    <#--            <span style="display: block"><strong>Prénom:</strong> ${expediteurPrenom}</span>-->
    <#--            <span><strong>Adresse:</strong> ${expediteurAdresse}</span>-->
    <#--        </div>-->

    <#--        <div>-->
    <#--            <span class="titre-section">Destinataire</span>-->
    <#--            <span style="display: block"><strong>Nom:</strong> ${destinataireNom}</span>-->
    <#--            <span style="display: block"><strong>Prénom:</strong> ${destinatairePrenom}</span>-->
    <#--            <span><strong>Adresse:</strong> ${destinataireAdresse}</span>-->
    <#--        </div>-->
    <#--    </div>-->



<#--    <div style="margin-bottom: 21px">-->
<#--        <table class="c">-->
<#--            <tr>-->
<#--                <th>Expéditeur</th>-->
<#--                <th>Destinataire</th>-->
<#--            </tr>-->
<#--            <tr>-->
<#--                <td>-->
<#--                    <div>-->
<#--                        <span style="display: block"><strong>Nom:</strong> ${expediteurNom}</span>-->
<#--                        <span style="display: block"><strong>Prénom:</strong> ${expediteurPrenom}</span>-->
<#--                        <span><strong>Adresse:</strong> ${expediteurAdresse}</span>-->
<#--                    </div>-->
<#--                </td>-->

<#--                <td>-->
<#--                    <div>-->
<#--                        <span style="display: block"><strong>Nom:</strong> ${destinataireNom}</span>-->
<#--                        <span style="display: block"><strong>Prénom:</strong> ${destinatairePrenom}</span>-->
<#--                        <span><strong>Adresse:</strong> ${destinataireAdresse}</span>-->
<#--                    </div>-->
<#--                </td>-->
<#--            </tr>-->
<#--        </table>-->
<#--    </div>-->



    <div style="margin-bottom: 21px">
        <table class="c">
            <tr>
                <th>Expéditeur</th>
                <th>Destinataire</th>
            </tr>
            <tr>
                <td>
                    <div>
                        <span style="display: block; padding-bottom: 11px">${expediteurFullName}</span>
                        <span style="display: block">${expediteurAdresse1}</span>
                        <span style="display: block">${expediteurAdresse2}</span>
                    </div>
                </td>

                <td>
                    <div>
                        <span style="display: block; padding-bottom: 11px">${destinataireFullName}</span>
                        <span style="display: block">${destinataireAdresse1}</span>
                        <span style="display: block">${destinataireAdresse2}</span>
                    </div>
                </td>
            </tr>
        </table>
    </div>




    <div>
        <span class="titre-section">Informations sur le coli</span>
        <table>
            <tr>
                <th>Nbr d'unité</th>
                <th>Poids</th>
                <th>Volume</th>
                <th>Description du coli</th>
            </tr>
            <tr>
                <td>${coliNombreUnite}</td>
                <td>${coliPoids} kg</td>
                <td>${coliVolume} m³</td>
                <td>${coliDescription}</td>
            </tr>
        </table>
    </div>

    <div style="margin-top: 35px">
        <span style="display: block; font-size: 41px; font-weight: bold">${envoiReference}</span>
        <div>
            <img src="${lienQrCode}" style="border: solid">
        </div>
    </div>

    <div style="margin-top: 25px;">
        <p style="font-size: 18px">Suivez votre envoi sur: <strong style="text-decoration: underline">www.spiral-express.com</strong></p>
        <span style="font-style: italic; font-size: 14px">Développer par: www.LapiEmo.com</span>
    </div>

</div>
</body>
</html>
