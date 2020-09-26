import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEnvoi } from 'app/shared/model/envoi.model';
import { EnvoiService } from './envoi.service';
import { EnvoiDeleteDialogComponent } from './envoi-delete-dialog.component';

@Component({
  selector: 'jhi-envoi',
  templateUrl: './envoi.component.html',
})
export class EnvoiComponent implements OnInit, OnDestroy {
  envois?: IEnvoi[];
  eventSubscriber?: Subscription;

  constructor(protected envoiService: EnvoiService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.envoiService.query().subscribe((res: HttpResponse<IEnvoi[]>) => (this.envois = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEnvois();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEnvoi): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEnvois(): void {
    this.eventSubscriber = this.eventManager.subscribe('envoiListModification', () => this.loadAll());
  }

  delete(envoi: IEnvoi): void {
    const modalRef = this.modalService.open(EnvoiDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.envoi = envoi;
  }
}
