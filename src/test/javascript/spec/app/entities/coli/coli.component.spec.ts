import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SpiralExpressTestModule } from '../../../test.module';
import { ColiComponent } from 'app/entities/coli/coli.component';
import { ColiService } from 'app/entities/coli/coli.service';
import { Coli } from 'app/shared/model/coli.model';

describe('Component Tests', () => {
  describe('Coli Management Component', () => {
    let comp: ColiComponent;
    let fixture: ComponentFixture<ColiComponent>;
    let service: ColiService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SpiralExpressTestModule],
        declarations: [ColiComponent],
      })
        .overrideTemplate(ColiComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ColiComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ColiService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Coli(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.colis && comp.colis[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
