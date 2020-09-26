import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SpiralExpressSharedModule } from 'app/shared/shared.module';
import { ColiComponent } from './coli.component';
import { ColiDetailComponent } from './coli-detail.component';
import { ColiUpdateComponent } from './coli-update.component';
import { ColiDeleteDialogComponent } from './coli-delete-dialog.component';
import { coliRoute } from './coli.route';

@NgModule({
  imports: [SpiralExpressSharedModule, RouterModule.forChild(coliRoute)],
  declarations: [ColiComponent, ColiDetailComponent, ColiUpdateComponent, ColiDeleteDialogComponent],
  entryComponents: [ColiDeleteDialogComponent],
})
export class SpiralExpressColiModule {}
