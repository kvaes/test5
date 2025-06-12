# Security Policy

## Supported Versions

We release patches for security vulnerabilities in the following versions:

| Version | Supported          |
| ------- | ------------------ |
| 1.x.x   | :white_check_mark: |

## Reporting a Vulnerability

We take the security of our software seriously. If you discover a security vulnerability, please follow these steps:

### How to Report

1. **Do not** open a public GitHub issue for the vulnerability
2. Email security reports to: [security@bics.com](mailto:security@bics.com)
3. Include the following information in your report:
   - Type of issue (e.g. buffer overflow, SQL injection, cross-site scripting, etc.)
   - Full paths of source file(s) related to the manifestation of the issue
   - The location of the affected source code (tag/branch/commit or direct URL)
   - Any special configuration required to reproduce the issue
   - Step-by-step instructions to reproduce the issue
   - Proof-of-concept or exploit code (if possible)
   - Impact of the issue, including how an attacker might exploit the issue

### What to Expect

- We will respond to your report within 48 hours
- We will provide a detailed response within 7 days indicating our evaluation of the report
- We will notify you when the issue is fixed
- We may ask for additional information or guidance

### Bug Bounty

Currently, we do not have a formal bug bounty program, but we recognize and appreciate the efforts of security researchers who help us maintain the security of our software.

## Security Measures

### Code Security

- All code changes go through peer review process
- Static code analysis is performed on all commits
- Dependencies are regularly updated and scanned for vulnerabilities
- Container images are scanned for security vulnerabilities

### Infrastructure Security

- Secrets are managed through secure secret management systems
- Access controls follow the principle of least privilege
- All deployments use secure communication channels
- Regular security audits are performed

### Development Security

- Developers follow secure coding practices
- Security training is provided to all team members
- Code is reviewed for security issues before merging
- Automated security testing is integrated into CI/CD pipeline

## Responsible Disclosure

We believe in responsible disclosure and will work with security researchers to address any vulnerabilities in a timely manner. We ask that you:

- Give us reasonable time to investigate and mitigate an issue before making any information public
- Do not access, modify, or delete data that doesn't belong to you
- Do not perform actions that could negatively impact our users or services
- Do not use social engineering attacks against our employees or contractors

## Contact

For any security-related questions or concerns, please contact us at:
- Email: [security@bics.com](mailto:security@bics.com)
- For general questions: [support@bics.com](mailto:support@bics.com)

## Updates

This security policy may be updated from time to time. Please check back regularly for any changes.

Last updated: December 12, 2024