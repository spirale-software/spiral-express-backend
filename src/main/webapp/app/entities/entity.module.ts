import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'coli',
        loadChildren: () => import('./coli/coli.module').then(m => m.SpiralExpressColiModule),
      },
      {
        path: 'envoi',
        loadChildren: () => import('./envoi/envoi.module').then(m => m.SpiralExpressEnvoiModule),
      },
      {
        path: 'client',
        loadChildren: () => import('./client/client.module').then(m => m.SpiralExpressClientModule),
      },
      {
        path: 'destinataire',
        loadChildren: () => import('./destinataire/destinataire.module').then(m => m.SpiralExpressDestinataireModule),
      },
      {
        path: 'personne',
        loadChildren: () => import('./personne/personne.module').then(m => m.SpiralExpressPersonneModule),
      },
      {
        path: 'adresse',
        loadChildren: () => import('./adresse/adresse.module').then(m => m.SpiralExpressAdresseModule),
      },
      {
        path: 'partenaire',
        loadChildren: () => import('./partenaire/partenaire.module').then(m => m.SpiralExpressPartenaireModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class SpiralExpressEntityModule {}
