import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SpiralExpressSharedModule } from 'app/shared/shared.module';
import { DestinataireComponent } from './destinataire.component';
import { DestinataireDetailComponent } from './destinataire-detail.component';
import { DestinataireUpdateComponent } from './destinataire-update.component';
import { DestinataireDeleteDialogComponent } from './destinataire-delete-dialog.component';
import { destinataireRoute } from './destinataire.route';

@NgModule({
  imports: [SpiralExpressSharedModule, RouterModule.forChild(destinataireRoute)],
  declarations: [DestinataireComponent, DestinataireDetailComponent, DestinataireUpdateComponent, DestinataireDeleteDialogComponent],
  entryComponents: [DestinataireDeleteDialogComponent],
})
export class SpiralExpressDestinataireModule {}
