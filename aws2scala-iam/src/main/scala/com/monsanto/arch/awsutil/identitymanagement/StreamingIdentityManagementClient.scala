package com.monsanto.arch.awsutil.identitymanagement

import akka.NotUsed
import akka.stream.scaladsl.Flow
import com.monsanto.arch.awsutil.StreamingAwsClient
import com.monsanto.arch.awsutil.identitymanagement.model._

trait StreamingIdentityManagementClient extends StreamingAwsClient {
  /** Returns a flow that given a list roles request will emit zero or more matching roles. */
  def roleLister: Flow[ListRolesRequest, Role, NotUsed]

  /** Returns a flow that a create role request will create and emmit the resulting role. */
  def roleCreator: Flow[CreateRoleRequest, Role, NotUsed]

  /** Returns a flow that given a role name will delete it and emit the name. */
  def roleDeleter: Flow[String, String, NotUsed]

  /** Returns a flow that will attach a managed policy to a role and then emit the role name. */
  def rolePolicyAttacher: Flow[AttachRolePolicyRequest, String, NotUsed]

  /** Returns a flow that will detach a managed policy from a role and then emit the role name. */
  def rolePolicyDetacher: Flow[DetachRolePolicyRequest, String, NotUsed]

  /** Returns a flow that, given a request to list attached policies for a role, will emit zero or more attached
    * policies.
    */
  def attachedRolePolicyLister: Flow[ListAttachedRolePoliciesRequest, AttachedPolicy, NotUsed]

  /** Returns a flow that will emit a user’s information given a request. */
  def userGetter: Flow[GetUserRequest, User, NotUsed]
}
