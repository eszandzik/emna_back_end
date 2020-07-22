import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './client-fournisseur.reducer';
import { IClientFournisseur } from 'app/shared/model/client-fournisseur.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IClientFournisseurDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ClientFournisseurDetail = (props: IClientFournisseurDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { clientFournisseurEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.clientFournisseur.detail.title">ClientFournisseur</Translate> [
          <b>{clientFournisseurEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <Translate contentKey="emnaBackEndApp.clientFournisseur.infoEntreprise">Info Entreprise</Translate>
          </dt>
          <dd>{clientFournisseurEntity.infoEntrepriseId ? clientFournisseurEntity.infoEntrepriseId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.clientFournisseur.societe">Societe</Translate>
          </dt>
          <dd>{clientFournisseurEntity.societeId ? clientFournisseurEntity.societeId : ''}</dd>
        </dl>
        <Button tag={Link} to="/client-fournisseur" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/client-fournisseur/${clientFournisseurEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ clientFournisseur }: IRootState) => ({
  clientFournisseurEntity: clientFournisseur.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ClientFournisseurDetail);
