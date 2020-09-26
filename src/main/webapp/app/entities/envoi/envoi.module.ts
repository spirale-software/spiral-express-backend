import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SpiralExpressSharedModule } from 'app/shared/shared.module';
import { EnvoiComponent } from './envoi.component';
import { EnvoiDetailComponent } from './envoi-detail.component';
import { EnvoiUpdateComponent } from './envoi-update.component';
import { EnvoiDeleteDialogComponent } from './envoi-delete-dialog.component';
import { envoiRoute } from './envoi.route';

@NgModule({
  imports: [SpiralExpressSharedModule, RouterModule.forChild(envoiRoute)],
  declarations: [EnvoiComponent, EnvoiDetailComponent, EnvoiUpdateComponent, EnvoiDeleteDialogComponent],
  entryComponents: [EnvoiDeleteDialogComponent],
})
export class SpiralExpressEnvoiModule {}
