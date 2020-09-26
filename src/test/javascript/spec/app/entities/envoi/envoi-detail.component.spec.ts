import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SpiralExpressTestModule } from '../../../test.module';
import { EnvoiDetailComponent } from 'app/entities/envoi/envoi-detail.component';
import { Envoi } from 'app/shared/model/envoi.model';

describe('Component Tests', () => {
  describe('Envoi Management Detail Component', () => {
    let comp: EnvoiDetailComponent;
    let fixture: ComponentFixture<EnvoiDetailComponent>;
    const route = ({ data: of({ envoi: new Envoi(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SpiralExpressTestModule],
        declarations: [EnvoiDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EnvoiDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EnvoiDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load envoi on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.envoi).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
